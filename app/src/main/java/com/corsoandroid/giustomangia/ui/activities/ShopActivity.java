package com.corsoandroid.giustomangia.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.adapters.MenuAdapter;
import com.corsoandroid.giustomangia.datamodels.Product;
import com.corsoandroid.giustomangia.datamodels.Restaurant;

import java.util.ArrayList;

/**
 * Created by Andrea on 31/01/2019.
 */

public class ShopActivity extends AppCompatActivity implements MenuAdapter.onQuantityChangeListener {


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
        menuAdapter = new MenuAdapter(this,creaPortate());
        menuRistorante.setAdapter(menuAdapter);
        pulsanteCheckout = findViewById(R.id.pulsanteCheckout);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)getRestaurant().getOrdineMinimo()*100);
        restaurant = getRestaurant();
        menuAdapter.setOnQuantityChangeListener(this);
    }

    public ArrayList<Product> creaPortate() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Product p1 = new Product("Whopper",5f);
        Product p2 = new Product("Chicken Bacon", 8f);
        Product p4 = new Product("Patatine", 1f);
        Product p5 = new Product("Insalata", 0.5f);
        Product p6 = new Product("Coca-cola", 1f);
        Product p7 = new Product("Cheeseburger", 3f);
        Product p8 = new Product("Chicken Royale", 4.50f);
        Product p9 = new Product("Chicken Wings", 1.50f);
        Product p10 = new Product("Waffles", 2.50f);
        Product p11 = new Product("Gelato", 1.20f);
        //Product p12 = new Product("Kid Menu", 4.00f);
        //Product p13 = new Product("Angry Whooper", 7.50f);
        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p4);
        productArrayList.add(p5);
        productArrayList.add(p6);
        productArrayList.add(p7);
        productArrayList.add(p8);
        productArrayList.add(p9);
        productArrayList.add(p10);
        productArrayList.add(p11);
        productArrayList.add(p7);
        productArrayList.add(p8);
        productArrayList.add(p9);
        productArrayList.add(p10);
        productArrayList.add(p11);
        //productArrayList.add(p12);
        //productArrayList.add(p13);

        return productArrayList;
    }

    private Restaurant getRestaurant() {
        return new Restaurant("Bottarolo","Via Casal Bruciato",10f,"https://qul.imgix.net/fce1c23e-c684-4af7-8158-69a1d4121d57/262537_landscape.jpg");
    }

    private void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    private void updateTotal(float f) {
        total+=f;
        totaleCarrello.setText(String.valueOf(total));
    }

    @Override
    public void onChange(float price) {
        updateTotal(price);
        updateProgressBar((int)(total*100));
        totaleCarrello.setText(String.format("TOTALE: %s $", String.valueOf(total)));
    }
}
