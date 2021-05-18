package com.hebert.xmaslist;


import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.hebert.xmaslist.data.UserDA;
import com.hebert.xmaslist.data.UserDACallback;
import com.hebert.xmaslist.model.User;

public class SettingsActivity extends OptionsMenu {

    private Toolbar toolbar;
    private TextView userName;
    private TextView user_birthday;
    private RadioGroup r_app_theme;
    private Button themeBTN;
    private User user = new User();
    private UserDA db = new UserDA();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        toolbar = findViewById(R.id.toolbar);
        userName = findViewById(R.id.user_name);
        user_birthday = findViewById(R.id.user_birthday);
        r_app_theme = findViewById(R.id.radio_groupe_theme);
        themeBTN = findViewById(R.id.changeTheme_BTN);

        setSupportActionBar(toolbar);

        themeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAppTheme(r_app_theme.getCheckedRadioButtonId());
            }
        });

        db.retrieveUser(new UserDACallback() {
            @Override
            public void getUserCallback(User newUser) {
                user = newUser;

                String name = user.getFirstName() + " " + user.getLastName();
                String birthday = user.getBirthday();

                userName.setText(name);
                user_birthday.setText(birthday);
            }
        });
    }

    private void changeAppTheme(int id) {

        if(id == R.id.default_theme) {

        }
        if(id == R.id.chirstmas_theme) {

        }
        else {

        }

    }
}