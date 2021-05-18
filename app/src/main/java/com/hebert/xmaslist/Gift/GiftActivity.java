package com.hebert.xmaslist.Gift;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.hebert.xmaslist.AddList;
import com.hebert.xmaslist.OptionsMenu;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.ViewModel.GiftViewModel;
import com.hebert.xmaslist.adapter.GiftListAdapter;
import com.hebert.xmaslist.data.GiftDA;
import com.hebert.xmaslist.data.GiftDACallback;
import com.hebert.xmaslist.data.ListDA;
import com.hebert.xmaslist.data.ListDACallback;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.model.GiftList;
import com.hebert.xmaslist.util.UtilKey;

import java.util.ArrayList;
import java.util.List;

public class GiftActivity extends OptionsMenu implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private FloatingActionButton addGiftButton;
    private Spinner spinner;
    private List<Gift> gifts = new ArrayList<>();
    private List<GiftList> giftLists = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();

    private GiftListAdapter giftListAdapter;
    private GiftViewModel giftViewModel;
    private ListDA listDB = new ListDA();
    private GiftDA giftDB = new GiftDA();

    int listPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        toolbar = findViewById(R.id.toolbar);
        addGiftButton = findViewById(R.id.open_gift_fragment);
        spinner = findViewById(R.id.spinner_list);

        setList();

        spinner.setOnItemSelectedListener(this);
        addGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddGiftDialog();
            }
        });
        setSupportActionBar(toolbar);
    }

    private void setList() {
        listDB.getList(new ListDACallback() {
            @Override
            public void getList(List<GiftList> list) {
                if(!list.isEmpty()) {
                    giftLists = list;

                    for (GiftList giftList : giftLists) {
                        subjects.add(giftList.getName());
                    }
                    setSpinner();
                    initGiftList();
                }
                else {
                    subjects.add("Your gift list");
                    setSpinner();
                }
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(dataAdapter);
    }


    void initGiftList() {
        gifts.clear();
        giftDB.getGift(giftLists.get(listPosition).getId(), new GiftDACallback() {
            @Override
            public void getGift(List<Gift> list) {
                gifts = list;
                initRecyclerView();
            }
        });
    }

        private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.gift_recyclerView);
        giftListAdapter = new GiftListAdapter(this, Gift.itemCallback);
        recyclerView.setAdapter(giftListAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        giftViewModel = new ViewModelProvider(this).get(GiftViewModel.class);
        giftViewModel.initGiftList(gifts);
        giftViewModel.getGiftList().observe(this, new Observer<List<Gift>>() {
            @Override
            public void onChanged(List<Gift> gifts) {
                giftListAdapter.submitList(gifts);
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
                    Gift gift;
                    String giftAsString = bundle.getString("giftAsString");
                    gift = gson.fromJson(giftAsString, Gift.class);

                    //edit contact or delete contact
                    int position = bundle.getInt("position");

                    if (bundle.getInt("type") == 1) {
                        updateGift(gift, position);
                    }
                    else {
                        deleteGift(gift, position);
                    }
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void deleteGift(Gift gift, int position) {
        giftViewModel.deleteGift(position);
        giftDB.deleteGift(gift);
    }

    private void updateGift(Gift gift, int position) {
        giftViewModel.updateGift(gift, position);
        giftDB.updateGift(gift);
    }


    private void showAddGiftDialog() {
        Intent intent = new Intent(this, AddGiftActivity.class);
        intent.putExtra("id", giftLists.get(listPosition).getId());
        startActivityForResult(intent, UtilKey.REQUEST_CODE_ADD);
    }

    public void addNewList(View view) {
        FragmentManager fm = getSupportFragmentManager();
        AddList addList = AddList.newInstance();
        addList.show(fm, "Fragment Add Gift");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        listPosition = i;
        if(!giftLists.isEmpty()) {
            initGiftList();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}