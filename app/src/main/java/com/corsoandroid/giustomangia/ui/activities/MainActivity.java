package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.adapters.RestorauntAdapters;
import com.corsoandroid.giustomangia.datamodels.Restoraunt;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView restorauntRV;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<Restoraunt> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restorauntRV = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RestorauntAdapters(this,getData());

        restorauntRV.setLayoutManager(layoutManager);
        restorauntRV.setAdapter(adapter); // PER UTILIZZARE UNA RECYCLERVIEW CI VOGLIONO UN LAYOUT MANAGER E UN ADAPTER
    }

    private ArrayList<Restoraunt> getData() {
        data = new ArrayList<>();
        Restoraunt r1 = new Restoraunt("Burger King ", "Via Tiburtina 58 ",10.00f);
        Restoraunt r2 = new Restoraunt("McDonald's ", "Via Pietralata 666 ", 8.00f);
        Restoraunt r3 = new Restoraunt("Tira e molla ", "Via Enna 4 ",10.00f);
        data.add(r1);
        data.add(r2);
        data.add(r3);

        return data;
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
