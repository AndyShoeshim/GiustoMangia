package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.adapters.RestaurantAdapters;
import com.corsoandroid.giustomangia.datamodels.Restaurant;
import com.corsoandroid.giustomangia.services.RestController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class MainActivity extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener {

    LinearLayout layout;
    RecyclerView restorauntRV; // le recycler view permette di visualizzare una grande quantità di dati in una view limitata
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapters adapter;
    // per utilizzare una recyclerview c'è bisogno di un layout manager e di un adapter. Il primo permette
    // di misurare e posizione le item view nella recyclerview e determinare quando riciclare gli elementi non più visibili.
    // L'adapter serve per legare i dati e gli itemview legati alla visualizzazione di questi dati alla recyclerview
    ArrayList<Restaurant> data = new ArrayList<>();
    SharedPreferences sharedPreferences;

    private static final String ENDPOINT = "restaurants";
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
        adapter = new RestaurantAdapters(this, data);
        adapter.setGrid(getSavedLayoutManager());
        restorauntRV.setAdapter(adapter);
        restorauntRV.setLayoutManager(layoutManager);
        RestController restController = new RestController(this);
        restController.getRequest(ENDPOINT,this,this);


        /*Request a string response from URL
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, // method type request
                url,  // destination url
                new Response.Listener<String>() { // answer listeners
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", " "+ response);
                        try {
                            JSONArray restorauntJSONArray = new JSONArray(response);
                            for(int i=0;i<restorauntJSONArray.length();i++) {
                                Restaurant r = new Restaurant(restorauntJSONArray.getJSONObject(i));
                                data.add(r);
                            }
                            adapter.setData(data);
                        } catch (JSONException e) {
                            Log.e("errorJSON",e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG"," " + error.toString());
            }
         }
        );

        queue.add(stringRequest);
        */

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

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("errorMessage",error.getMessage());
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
                Restaurant r = new Restaurant(jsonArray.getJSONObject(i));
                data.add(r);
            }
            adapter.setData(data);
        } catch (JSONException e) {
            Log.e("exceptionMessage",e.getMessage());
        }
    }
}
