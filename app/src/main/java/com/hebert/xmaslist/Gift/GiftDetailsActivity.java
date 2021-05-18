package com.hebert.xmaslist.Gift;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.hebert.xmaslist.OptionsMenu;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.model.Gift;
import com.hebert.xmaslist.util.UtilKey;

public class GiftDetailsActivity extends OptionsMenu implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView productName;
    private ImageView icon;
    private TextView storeTV;
    private TextView costTV;
    private TextView personAssignTV;

    private FloatingActionButton editBTN;
    private FloatingActionButton deleteBTN;
    private Button doneBTN;
    private Gift gift = new Gift();


    Intent intentFromWishActivity;
    Intent intentToEdit;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_details);

        toolbar = findViewById(R.id.toolbar);
        productName = findViewById(R.id.title_gift);
        icon = findViewById(R.id.icon_gift_view);
        storeTV = findViewById(R.id.product_store);
        costTV = findViewById(R.id.product_cost);
        personAssignTV = findViewById(R.id.person_assign);

        editBTN = findViewById(R.id.edit);
        deleteBTN = findViewById(R.id.delete);
        doneBTN = findViewById(R.id.details_done_button);

        setSupportActionBar(toolbar);
        editBTN.setOnClickListener(this);
        deleteBTN.setOnClickListener(this);
        doneBTN.setOnClickListener(this);

        intentFromWishActivity = getIntent();
        Bundle bundle = intentFromWishActivity.getExtras();
        if(bundle != null) {
            Gson gson = new Gson();
            String giftAsString = bundle.getString("giftAsString");
            gift = gson.fromJson(giftAsString, Gift.class);
            position = bundle.getInt("position");

            initateViews(gift);
        }

    }

    @Override
    public void onClick(View view) {

        Gson gson = new Gson();
        String giftAsString = gson.toJson(gift);

        switch(view.getId()) {
            case R.id.edit:
                intentToEdit = new Intent(GiftDetailsActivity.this, UpdateGiftActivity.class);
                intentToEdit.putExtra("giftAsString", giftAsString);
                intentToEdit.putExtra("position", position);
                startActivityForResult(intentToEdit, UtilKey.REQUEST_CODE_EDIT);
                break;

            case R.id.delete:
                intentFromWishActivity.putExtra("giftAsString", giftAsString);
                intentFromWishActivity.putExtra("position", position);
                intentFromWishActivity.putExtra("type", 2);
                setResult(RESULT_OK, intentFromWishActivity);
                finish();
                break;

            case R.id.details_done_button:
                intentFromWishActivity.putExtra("giftAsString", giftAsString);
                intentFromWishActivity.putExtra("position", position);
                intentFromWishActivity.putExtra("type", 1);
                setResult(RESULT_OK, intentFromWishActivity);
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == UtilKey.REQUEST_CODE_EDIT) {

            if (resultCode == RESULT_OK) {

                Bundle bundle = data.getExtras();
                if (bundle != null) {

                    Gson gson = new Gson();
                    String giftAsString = bundle.getString("giftAsString");
                    gift = gson.fromJson(giftAsString, Gift.class);

                    initateViews(gift);
                    doneBTN.setVisibility(View.VISIBLE);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initateViews(Gift gift) {

        productName.setText(gift.getName());
        storeTV.setText(gift.getStore());
        costTV.setText(gift.getCost().toString());
        personAssignTV.setText(gift.getPersonAssign());

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(gift.getIcon())
                .into(icon);
    }
}