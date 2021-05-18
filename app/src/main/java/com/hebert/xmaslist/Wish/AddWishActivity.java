package com.hebert.xmaslist.Wish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.hebert.xmaslist.AddIcon;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.adapter.IconRecyclerViewAdapter;
import com.hebert.xmaslist.data.WishDA;
import com.hebert.xmaslist.model.Wish;

import java.util.ArrayList;

public class AddWishActivity extends AppCompatActivity {

    private EditText productName;
    private RadioGroup wishCategory;
    private Button newWishButton;
    private ImageButton imageButton;
    private Wish wish = new Wish();
    private WishDA db = new WishDA();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        productName = findViewById(R.id.product_name);
        wishCategory = findViewById(R.id.radio_group_category);
        newWishButton = findViewById(R.id.add_wish_button);
        imageButton = findViewById(R.id.add_icon);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddIcon();
            }
        });

        newWishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wish.setName(productName.getText().toString());
                wish.setIcon("https://img.icons8.com/plasticine/2x/christmas-tree.png");
                addNewWish(wish);
            }
        });


    }

    //this method is displaying the fragment to let the user choose his icon
    private void showAddIcon() {
        FragmentManager fm = getSupportFragmentManager();
        AddIcon addIcon = AddIcon.newInstance();
        addIcon.show(fm, "Fragment add wish");
    }

    //method called when the user wants to add a new wish to his list
    void addNewWish(Wish wish) {
        int radioButtonId = wishCategory.getCheckedRadioButtonId();

        if(radioButtonId == R.id.like_category) {
            wish.setCategory("Like");
        }
        else if (radioButtonId == R.id.want_category) {
            wish.setCategory("Want");
        }
        else if (radioButtonId == R.id.favorite_category){
            wish.setCategory("Favorite");
        }

        db.addNewWish(wish);
        Intent intent = new Intent(AddWishActivity.this, WishActivity.class);
        startActivity(intent);
    }

}