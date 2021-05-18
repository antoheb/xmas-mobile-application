package com.hebert.xmaslist.Gift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
import com.hebert.xmaslist.data.GiftDA;
import com.hebert.xmaslist.data.WishDA;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.model.Wish;

import java.util.ArrayList;

public class AddGiftActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productStore;
    private EditText productPersonAssign;
    private EditText productCost;
    private Button newGiftButton;
    private ImageButton imageButton;
    private Gift gift = new Gift();
    private String listId;

    private GiftDA db = new GiftDA();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);

        productName = findViewById(R.id.product_name);
        productStore = findViewById(R.id.gift_store);
        productCost = findViewById(R.id.cost_ET);
        productPersonAssign = findViewById(R.id.person_assign);
        newGiftButton = findViewById(R.id.add_gift_button);
        imageButton = findViewById(R.id.add_icon);

        Bundle bundle;
        bundle = getIntent().getExtras();
        if(!bundle.isEmpty()) {
            listId = bundle.getString("id");
        }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddIcon();
            }
        });

        newGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gift.setName(productName.getText().toString());
                gift.setIcon("https://img.icons8.com/plasticine/2x/christmas-tree.png");
                gift.setStore(productStore.getText().toString());
                gift.setCost(Double.parseDouble(productCost.getText().toString()));
                gift.setPersonAssign(productPersonAssign.getText().toString());
                gift.setListId(listId);

                db.addNewGift(gift);
                Intent intent = new Intent(AddGiftActivity.this, GiftActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAddIcon() {
        FragmentManager fm = getSupportFragmentManager();
        AddIcon addIcon = AddIcon.newInstance();
        addIcon.show(fm, "Fragment add wish");
    }
}