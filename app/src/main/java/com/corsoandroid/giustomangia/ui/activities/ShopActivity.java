package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.adapters.MenuAdapter;
import com.corsoandroid.giustomangia.datamodels.Product;
import com.corsoandroid.giustomangia.datamodels.Restaurant;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class ShopActivity extends AppCompatActivity implements MenuAdapter.onQuantityChangeListener,View.OnClickListener {


    RelativeLayout layout;
    ImageView logoRisotorante;
    TextView nomeRistorante, totaleCarrello;
    RecyclerView menuRistorante;
    RecyclerView.LayoutManager manager;
    MenuAdapter menuAdapter;
    ProgressBar progressBar;
    Button pulsanteCheckout;

    Restaurant restaurant;

    private float total;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initialize();
        //nomeRistorante.setText(getIntent().getStringExtra("nomeRistorante"));
        nomeRistorante.setText(restaurant.getNome());
        Glide.with(this).load(restaurant.getLogo()).into(logoRisotorante);

    }


    private void initialize() {
        layout = findViewById(R.id.relativeLayoutCheckout);
        logoRisotorante = findViewById(R.id.logoRistorante);
        nomeRistorante = findViewById(R.id.nomeRistorante);
        totaleCarrello = findViewById(R.id.totaleCarrello);
        menuRistorante = findViewById(R.id.menuRistorante);
        manager = new LinearLayoutManager(this);
        menuRistorante.setLayoutManager(manager);
        menuAdapter = new MenuAdapter(this, Test.creaPortate());
        menuRistorante.setAdapter(menuAdapter);
        pulsanteCheckout = findViewById(R.id.pulsanteCheckout);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)getRestaurant().getOrdineMinimo()*100);
        restaurant = getRestaurant();
        menuAdapter.setOnQuantityChangeListener(this);
        pulsanteCheckout.setOnClickListener(this);
    }


    private Restaurant getRestaurant() {
        return new Restaurant("Bottarolo","Via Casal Bruciato",10f,"https://qul.imgix.net/fce1c23e-c684-4af7-8158-69a1d4121d57/262537_landscape.jpg");
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
        if (getRestaurant().getOrdineMinimo()<=f) {
            pulsanteCheckout.setEnabled(true);
        } else if (getRestaurant().getOrdineMinimo()>f) {
            pulsanteCheckout.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==pulsanteCheckout.getId()){
            Intent i = new Intent(this,CheckoutActivity.class);
            startActivity(i);
        }
    }
}
