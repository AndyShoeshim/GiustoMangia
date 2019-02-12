package com.corsoandroid.giustomangia.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.corsoandroid.giustomangia.OnProductEliminatedListener;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.adapters.CarrelloAdapter;
import com.corsoandroid.giustomangia.datamodels.Ordine;

/**
 * Created by Andrea on 31/01/2019.
 */

public class CheckoutActivity extends AppCompatActivity implements OnProductEliminatedListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CarrelloAdapter adapter;
    TextView totale, nomeRistorante, indirizzoRistorante;
    Button pagamento;
    Ordine order = Test.getOrdine();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        totale = findViewById(R.id.totaleCosto);
        pagamento = findViewById(R.id.paymentButton);
        nomeRistorante = findViewById(R.id.nomeRistoranteOrdine);
        indirizzoRistorante = findViewById(R.id.indirizzoRistoranteOrdine);
        setNames();
        layoutManager = new LinearLayoutManager(this);
        adapter = new CarrelloAdapter(this,order);
        adapter.setO(this);
        recyclerView = findViewById(R.id.carrelloProdotti);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void setNames() {
        nomeRistorante.setText(order.restaurant.getNome());
        indirizzoRistorante.setText(order.restaurant.getIndirizzo());
        totale.append(" " + String.valueOf(order.calcoloTotale()));
    }

    @Override
    public void onChangeTotal() {
        totale.setText("TOTALE: "+String.valueOf(order.calcoloTotale()));
    }
}