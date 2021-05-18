package com.hebert.xmaslist.Wish;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.hebert.xmaslist.AddIcon;
import com.hebert.xmaslist.OptionsMenu;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.ViewModel.WishViewModel;
import com.hebert.xmaslist.adapter.WishListAdapter;
import com.hebert.xmaslist.data.WishDA;
import com.hebert.xmaslist.data.WishDACallback;
import com.hebert.xmaslist.model.Wish;
import com.hebert.xmaslist.util.UtilKey;

import java.util.ArrayList;
import java.util.List;

public class WishActivity extends OptionsMenu {

    private Toolbar toolbar;
    private FloatingActionButton addWishButton;
    private TabLayout tabLayout;
    private List<Wish> wishes = new ArrayList<>();
    private WishListAdapter wishListAdapter;
    private WishViewModel wishViewModel;

    private WishDA db = new WishDA();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);

        toolbar = findViewById(R.id.toolbar);
        addWishButton = findViewById(R.id.open_wish_fragment);
        tabLayout = findViewById(R.id.tab_layout);

        setSupportActionBar(toolbar);

        //create tab for tablayout
        tabLayout.addTab(tabLayout.newTab().setText("Like"));
        tabLayout.addTab(tabLayout.newTab().setText("Want"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorite"));
        initWish("Like");

        addWishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddWish();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0) {
                    initWish("Like");
                }
                else if (tabLayout.getSelectedTabPosition() == 1) {
                    initWish("Want");
                }
                else {
                    initWish("Favorite");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == UtilKey.REQUEST_CODE_DETAILS) {
            if (resultCode == RESULT_OK) {

                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Gson gson = new Gson();
                    Wish wish;
                    String wishAsString = bundle.getString("wishAsString");
                    wish = gson.fromJson(wishAsString, Wish.class);

                    //edit contact or delete contact
                    int position = bundle.getInt("position");

                    if (bundle.getInt("type") == 1) {
                        updateWish(wish, position);
                    }
                    else {
                        deleteWish(wish, position);
                    }
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void initWish(String category) {
        wishes.clear();

        db.getWish(category, new WishDACallback() {
            @Override
            public void getWish(List<Wish> list) {
                wishes = list;
                initRecyclerView();
            }
        });


    }

    private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.wish_recyclerView);
        wishListAdapter = new WishListAdapter(this, Wish.itemCallback);
        recyclerView.setAdapter(wishListAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        wishViewModel = new ViewModelProvider(this).get(WishViewModel.class);
        wishViewModel.initWishList(wishes);
        wishViewModel.getWishList().observe(this, new Observer<List<Wish>>() {
            @Override
            public void onChanged(List<Wish> wishes) {
                wishListAdapter.submitList(wishes);
            }
        });
    }

    private void showAddWish() {
        Intent intent = new Intent(this, AddWishActivity.class);
        startActivityForResult(intent, UtilKey.REQUEST_CODE_ADD);
    }

    private void deleteWish(Wish wish, int position) {
        wishViewModel.deleteWish(position);
        db.deleteWish(wish);
    }

    private void updateWish(Wish wish, int position) {
        wishViewModel.updateWish(wish, position);
        db.updateWish(wish);
    }
}