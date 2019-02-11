package com.corsoandroid.giustomangia.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.adapters.CarrelloAdapter;

/**
 * Created by Andrea on 31/01/2019.
 */

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CarrelloAdapter adapter;
    TextView totale;
    Button pagamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        totale = findViewById(R.id.totaleCosto);
        pagamento = findViewById(R.id.paymentButton);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CarrelloAdapter(this,Test.getOrdine());
        recyclerView = findViewById(R.id.carrelloProdotti);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}