package com.corsoandroid.giustomangia.ui.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.adapters.MenuAdapter;
import com.corsoandroid.giustomangia.datamodels.Ordine;
import com.corsoandroid.giustomangia.datamodels.Product;
import com.corsoandroid.giustomangia.datamodels.Restaurant;
import com.corsoandroid.giustomangia.services.RestController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class ShopActivity extends AppCompatActivity implements MenuAdapter.onQuantityChangeListener,View.OnClickListener, Response.Listener<String>,Response.ErrorListener {


    RelativeLayout layout;
    ImageView logoRisotorante;
    TextView nomeRistorante, totaleCarrello;
    RecyclerView menuRistorante;
    ArrayList<Product> arrayList = new ArrayList<>();
    RecyclerView.LayoutManager manager;
    MenuAdapter menuAdapter;
    ProgressBar progressBar;
    Button pulsanteCheckout;
    Menu m;

    Restaurant restaurant;

    private float total;
    private String endpoint;
    public String token;
    public static final int REQUEST_CODE = 101;

    public LoginShop loginShop;
    public LogoutShop logoutShop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        endpoint = "restaurants".concat("/" + getIntent().getStringExtra("idRistorante"));
        initialize();
        nomeRistorante.setText(getIntent().getStringExtra("nomeRistorante"));
        Glide.with(this).load(getIntent().getStringArrayExtra("logoRistorante")).into(logoRisotorante);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        loginShop = new LoginShop();
        logoutShop = new LogoutShop();

        LocalBroadcastManager.getInstance(this).registerReceiver(loginShop, new IntentFilter(Utilities.LOGIN_ACTION));
        LocalBroadcastManager.getInstance(this).registerReceiver(loginShop, new IntentFilter(Utilities.LOGOUT_ACTION));
        //token = getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("tokenLogin",null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(loginShop);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(logoutShop);
    }

    @Override
    protected void onResume() {
        super.onResume();
        token = getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("tokenLogin",null);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        token = getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("tokenLogin",null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater(); // classe che trasforma risorsa XML in oggetto
        mi.inflate(R.menu.menu_main, menu);
        this.m = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.login_menu){
            if(item.getTitle().equals("PROFILE"))
            {
                startActivity(new Intent(this,ProfileActivity.class));
            } else if(item.getTitle().equals("LOGIN"))
            {
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        layout = findViewById(R.id.relativeLayoutCheckout);
        logoRisotorante = findViewById(R.id.logoRistorante);
        nomeRistorante = findViewById(R.id.nomeRistorante);
        totaleCarrello = findViewById(R.id.totaleCarrello);
        menuRistorante = findViewById(R.id.menuRistorante);

        manager = new LinearLayoutManager(this);
        menuRistorante.setLayoutManager(manager);

        RestController restController = new RestController(this);
        restController.getRequest(endpoint,this,this);

        menuAdapter = new MenuAdapter(this, arrayList);
        menuRistorante.setAdapter(menuAdapter);
        //RestController restController = new RestController(this);
        //restController.getRequest(endpoint,this,this);

        pulsanteCheckout = findViewById(R.id.pulsanteCheckout);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)getIntent().getFloatExtra("ordineMinimo",5f)*100);

        menuAdapter.setOnQuantityChangeListener(this);
        pulsanteCheckout.setOnClickListener(this);
    }


    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    private void updateTotal(float f) {
        if (total >= 0) {
            total += f;
            totaleCarrello.setText(String.valueOf(total));
        } else if (total == 0)
            totaleCarrello.setText("0");
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgressBar((int)(total*100));
        totaleCarrello.setText(String.format("TOTALE: %s $", String.valueOf(total)));
        checkOutEnabled(total);
    }

    public void checkOutEnabled(float f) {
        if (getIntent().getFloatExtra("ordineMinimo",5f)<=f) {
            pulsanteCheckout.setEnabled(true);
        } else if (getIntent().getFloatExtra("ordineMinimo",5f)>f) {
            pulsanteCheckout.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==pulsanteCheckout.getId()){
            /*
            Intent i = new Intent(this,CheckoutActivity.class);
            startActivity(i);
            */
            if(token==null){
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this,CheckoutActivity.class);
                startActivity(i);
            }
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode== Activity.RESULT_OK) {
            Intent i = new Intent(this,CheckoutActivity.class);
            startActivity(i);
        }
    } */


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("errorMessage",error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray =  jsonObject.getJSONArray("products");
            for(int i=0;i<jsonArray.length();i++){
                arrayList.add(new Product(jsonArray.getJSONObject(i)));
            }
            menuAdapter.setData(arrayList);
        } catch (JSONException e) {
            Log.e("jsonException",e.getMessage());
        }
    }

    public class LoginShop extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            m.findItem(R.id.login_menu).setTitle("PROFILE");
        }
    }

    public class LogoutShop extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            m.findItem(R.id.login_menu).setTitle("LOGIN");
        }
    }

}
