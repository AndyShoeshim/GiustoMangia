package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.corsoandroid.giustomangia.R;

/**
 * Created by Andrea on 31/01/2019.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // classe che trasforma risorsa XML in oggetto
        mi.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override // metodo per gestire gli item del menu
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_menu) {
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        } else if(item.getItemId()==R.id.checkout_menu) {
            startActivity(new Intent(this,CheckoutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
