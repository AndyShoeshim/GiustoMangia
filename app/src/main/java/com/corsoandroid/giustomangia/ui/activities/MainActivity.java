package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.adapters.RestaurantAdapters;
import com.corsoandroid.giustomangia.datamodels.Restaurant;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    RecyclerView restorauntRV; // le recycler view permette di visualizzare una grande quantità di dati in una view limitata
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapters adapter;
    // per utilizzare una recyclerview c'è bisogno di un layout manager e di un adapter. Il primo permette
    // di misurare e posizione le item view nella recyclerview e determinare quando riciclare gli elementi non più visibili.
    // L'adapter serve per legare i dati e gli itemview legati alla visualizzazione di questi dati alla recyclerview
    ArrayList<Restaurant> data;
    SharedPreferences sharedPreferences;

    private static final String SharedPrefs = "com.corsoandroid.giustomangia.generalpref";
    private static final String View_Mode = "View_Mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layoutMain);
        checkDarkTheme();
        restorauntRV = findViewById(R.id.recyclerView);
        layoutManager = getLayoutManager(getSavedLayoutManager());
        adapter = new RestaurantAdapters(this, Test.getData());
        adapter.setGrid(getSavedLayoutManager());

        restorauntRV.setAdapter(adapter);
        restorauntRV.setLayoutManager(layoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // classe che trasforma risorsa XML in oggetto
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // metodo per gestire gli item del menu
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.changeLayout) {
            if (!adapter.isGrid()) {
                setLayoutManager();
                item.setIcon(R.drawable.ic_format_align_justify_black_24dp);
            } else {
                setLayoutManager();
                item.setIcon(R.drawable.ic_apps_black_24dp);
            }
        } // azioni da svolgere alla pressione del tasto per cambiare layout
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, LoginActivity.class));
            return true; // azioni da svolgere alla pressione dell'opzione LOGIN
        } else if (item.getItemId() == R.id.checkout_menu) {
            startActivity(new Intent(this, CheckoutActivity.class));
            return true; // azioni da svolgere alla pressione dell'opzione CHECKOUT
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkDarkTheme() {
        if (getIntent().getBooleanExtra("statoTema", false)) {
            layout.setBackgroundColor(getResources().getColor(R.color.colorDarkTheme, null));
        } else
            layout.setBackgroundColor(Color.WHITE);
    } // metodo per settare il tema del layout

    private void setLayoutManager() {
        layoutManager = getLayoutManager(!adapter.isGrid());
        adapter.setGrid(!adapter.isGrid());
        restorauntRV.setLayoutManager(layoutManager);
        restorauntRV.setAdapter(adapter);
        saveLayoutManager(adapter.isGrid());
    }

    private void saveLayoutManager(boolean isGridLayout) {
        sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(View_Mode,isGridLayout);
        editor.apply();
    } // metodo per salvare nelle shared preferences il layout scelto dall'utente

    private boolean getSavedLayoutManager() {
        sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        return  sharedPreferences.getBoolean(View_Mode,false);
    } // metodo per caricare il valore booleano relativo alla scelta del layout dell'utente

    private RecyclerView.LayoutManager getLayoutManager(boolean isGridLayout) {
        return  isGridLayout ? new GridLayoutManager(this,2) : new LinearLayoutManager(this);
    }
}
