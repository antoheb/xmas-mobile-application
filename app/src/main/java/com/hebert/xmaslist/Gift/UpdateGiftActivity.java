package com.hebert.xmaslist.Gift;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.gson.Gson;
import com.hebert.xmaslist.AddIcon;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.adapter.IconRecyclerViewAdapter;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.model.Wish;

import java.util.ArrayList;

public class UpdateGiftActivity extends AppCompatActivity {

    private ArrayList<String> imageURLs = new ArrayList<>();

    private EditText productName;
    private EditText productStore;
    private EditText productPersonAssign;
    private EditText productCost;
    private Button submitBTN;
    private ImageButton imageButton;
    private Gift gift = new Gift();

    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gift);

        productName = findViewById(R.id.product_name);
        productStore = findViewById(R.id.gift_store);
        productCost = findViewById(R.id.cost_ET);
        productPersonAssign = findViewById(R.id.person_assign);
        submitBTN = findViewById(R.id.submit);
        imageButton = findViewById(R.id.add_icon);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddIcon();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (!bundle.isEmpty()) {
            Gson gson = new Gson();

            String giftAsString = bundle.getString("giftAsString");
            gift = gson.fromJson(giftAsString, Gift.class);
            position = bundle.getInt("position");

            initateViews(gift);

            submitBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gift.setName(productName.getText().toString());
                    gift.setIcon("https://img.icons8.com/plasticine/2x/christmas-tree.png");
                    gift.setStore(productStore.getText().toString());
                    gift.setCost(Double.parseDouble(productCost.getText().toString()));
                    gift.setPersonAssign(productPersonAssign.getText().toString());

                    Gson gson = new Gson();
                    String giftAsString = gson.toJson(gift);

                    Intent intent = new Intent();
                    intent.putExtra("giftAsString", giftAsString);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    private void initateViews(Gift gift) {

        productName.setText(gift.getName());
        productStore.setText(gift.getStore());
        productCost.setText(gift.getCost().toString());
        productPersonAssign.setText(gift.getPersonAssign());
    }

    //this method is displaying the fragment to let the user choose his icon
    private void showAddIcon() {
        FragmentManager fm = getSupportFragmentManager();
        AddIcon addIcon = AddIcon.newInstance();
        addIcon.show(fm, "Fragment add wish");
    }
}