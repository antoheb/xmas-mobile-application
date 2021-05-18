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

import com.google.gson.Gson;
import com.hebert.xmaslist.AddIcon;
import com.hebert.xmaslist.R;
import com.hebert.xmaslist.adapter.IconRecyclerViewAdapter;
import com.hebert.xmaslist.model.Wish;
import com.hebert.xmaslist.util.UtilKey;

import java.util.ArrayList;

public class UpdateWishActivity extends AppCompatActivity {

    private ArrayList<String> imageURLs = new ArrayList<>();

    private EditText productName;
    private RadioGroup wishCategory;
    private Button doneBTN;
    private ImageButton imageButton;
    private Wish wish = new Wish();
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wish);

        productName = findViewById(R.id.product_name);
        wishCategory = findViewById(R.id.radio_group_category);
        doneBTN = findViewById(R.id.submit);
        imageButton = findViewById(R.id.add_icon);

        Bundle bundle = getIntent().getExtras();

        if (!bundle.isEmpty()) {
            Gson gson = new Gson();

            String wishAsString = bundle.getString("wishAsString");
            wish = gson.fromJson(wishAsString, Wish.class);
            position = bundle.getInt("position");

            initateViews(wish);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAddIcon();
                }
            });

            doneBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wish.setIcon("https://img.icons8.com/plasticine/2x/christmas-tree.png");
                    wish.setName(productName.getText().toString());

                    int radioButtonId = wishCategory.getCheckedRadioButtonId();

                    if (radioButtonId == R.id.like_category) {
                        wish.setCategory("Like");
                    } else if (radioButtonId == R.id.want_category) {
                        wish.setCategory("Want");
                    } else if (radioButtonId == R.id.favorite_category) {
                        wish.setCategory("Favorite");
                    }

                    Gson gson = new Gson();
                    String wishAsString = gson.toJson(wish);

                    Intent intent = new Intent();
                    intent.putExtra("wishAsString", wishAsString);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            });
        }
    }

    private void initateViews(Wish wish) {

        productName.setText(wish.getName());

        if (wish.getCategory().equals("Like")) {
            wishCategory.check(R.id.like_category);
        } else if (wish.getCategory().equals("Want")) {
            wishCategory.check(R.id.want_category);
        } else if (wish.getCategory().equals("Favorite")) {
            wishCategory.check(R.id.favorite_category);
        }
    }

    //this method is displaying the fragment to let the user choose his icon
    private void showAddIcon() {
        FragmentManager fm = getSupportFragmentManager();
        AddIcon addIcon = AddIcon.newInstance();
        addIcon.show(fm, "Fragment add wish");
    }


}

