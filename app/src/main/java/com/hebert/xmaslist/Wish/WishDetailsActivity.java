package com.hebert.xmaslist.Wish;

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
import com.hebert.xmaslist.model.Wish;
import com.hebert.xmaslist.util.UtilKey;


public class WishDetailsActivity extends OptionsMenu implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView productName;
    private ImageView icon;
    private TextView category;
    private FloatingActionButton editBTN;
    private FloatingActionButton deleteBTN;
    private Button doneBTN;
    private Wish wish = new Wish();


    Intent intentFromWishActivity;
    Intent intentToEdit;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_details);

        toolbar = findViewById(R.id.toolbar);
        productName = findViewById(R.id.title_product_name);
        icon = findViewById(R.id.icon_image_view);
        category = findViewById(R.id.category);
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
            String wishAsString = bundle.getString("wishAsString");
            wish = gson.fromJson(wishAsString, Wish.class);
            position = bundle.getInt("position");

            initateViews(wish);
        }

    }


    @Override
    public void onClick(View view) {

        Gson gson = new Gson();
        String wishAsString = gson.toJson(wish);

        switch(view.getId()) {
            case R.id.edit:
                intentToEdit = new Intent(WishDetailsActivity.this, UpdateWishActivity.class);
                intentToEdit.putExtra("wishAsString", wishAsString);
                intentToEdit.putExtra("position", position);
                startActivityForResult(intentToEdit, UtilKey.REQUEST_CODE_EDIT);
                break;

            case R.id.delete:
                intentFromWishActivity.putExtra("wishAsString", wishAsString);
                intentFromWishActivity.putExtra("position", position);
                intentFromWishActivity.putExtra("type", 2);
                setResult(RESULT_OK, intentFromWishActivity);
                finish();
                break;

            case R.id.details_done_button:
                intentFromWishActivity.putExtra("wishAsString", wishAsString);
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
                    String wishAsString = bundle.getString("wishAsString");
                    wish = gson.fromJson(wishAsString, Wish.class);

                    initateViews(wish);
                    doneBTN.setVisibility(View.VISIBLE);
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initateViews(Wish wish) {

        productName.setText(wish.getName());
        category.setText(wish.getCategory());

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(wish.getIcon())
                .into(icon);
    }


}