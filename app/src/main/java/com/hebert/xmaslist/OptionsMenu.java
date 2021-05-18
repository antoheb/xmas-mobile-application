package com.hebert.xmaslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hebert.xmaslist.Gift.GiftActivity;
import com.hebert.xmaslist.Wish.WishActivity;

public class OptionsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_item_about:
                Toast.makeText(getApplicationContext(), "About Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_item_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.action_gift:
                startActivity(new Intent(this, GiftActivity.class));
                break;

            case R.id.action_wish:
                startActivity(new Intent(this, WishActivity.class));
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
