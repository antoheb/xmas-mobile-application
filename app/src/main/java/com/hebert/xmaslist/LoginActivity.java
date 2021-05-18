package com.hebert.xmaslist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.hebert.xmaslist.Wish.WishActivity;
import com.hebert.xmaslist.data.UserDA;
import com.hebert.xmaslist.data.UserDACallback;
import com.hebert.xmaslist.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText firstNameET;
    private EditText lastNameET;
    private DatePicker datePicker;
    private Button loginBTN;
    private User user = new User();
    private UserDA db = new UserDA();
    private SharedPreferences themePrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db.retrieveUser(new UserDACallback() {
            @Override
            public void getUserCallback(User user) {
                if(user != null)
                    startWishActivity();
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firstNameET = findViewById(R.id.first_name);
        lastNameET = findViewById(R.id.last_name);
        datePicker = findViewById(R.id.birthday);
        loginBTN = findViewById(R.id.login);


        loginBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String birthday = datePicker.getMonth() + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear();
                user = new User(firstNameET.getText().toString(), lastNameET.getText().toString(), birthday);

                db.createUser(user);
                startWishActivity();
            }
        });

    }

    private void startWishActivity() {
        Intent intent = new Intent(LoginActivity.this, WishActivity.class);
        startActivity(intent);
    }
}
