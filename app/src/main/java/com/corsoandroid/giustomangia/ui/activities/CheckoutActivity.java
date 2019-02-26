package com.corsoandroid.giustomangia.ui.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.corsoandroid.giustomangia.OnProductEliminatedListener;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.adapters.CarrelloAdapter;
import com.corsoandroid.giustomangia.datamodels.Ordine;
import com.corsoandroid.giustomangia.datamodels.Product;
import com.corsoandroid.giustomangia.services.AppDatabase;
import com.corsoandroid.giustomangia.services.RestController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrea on 31/01/2019.
 */

public class CheckoutActivity extends AppCompatActivity implements OnProductEliminatedListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String endpoint = "orders";
    private static  String headers;

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

        new GetOrder().execute();

        headers = "Bearer " +  getSharedPreferences("sharedPref",Context.MODE_PRIVATE).getString("tokenLogin",null);
        Map<String,String> map = new HashMap<>();
        map.put("Authorization",headers);
        RestController r = new RestController(this);
        r.postCORequest(endpoint,buildJSON(),this,map,this);
    }


    public void setNames() {
        nomeRistorante.setText(order.restaurant.getNome());
        indirizzoRistorante.setText(order.restaurant.getIndirizzo());
    }

    @Override
    public void onChangeTotal() {
        totale.setText("TOTALE: "+String.valueOf(order.calcoloTotale()));
    }


    public JSONObject buildJSON () {

        JSONObject jsonObject = new JSONObject();
        String idR = getIntent().getStringExtra("idRistorante");
        String idU = getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("idUser",null);
        Log.d("debug",idR);
        Log.d("debug",idU);
        double totale = (double) getIntent().getFloatExtra("totale",5f);

        JSONObject o1 = Utilities.parseToJSON(Test.getProducts().get(0));
        JSONObject o2 = Utilities.parseToJSON(Test.getProducts().get(1));
        JSONObject o3 = Utilities.parseToJSON(Test.getProducts().get(2));

        JSONArray jsonArray = new JSONArray();


        try {

            jsonObject.put("restaurant",idR);
            jsonObject.put("user",idU);
            jsonObject.put("amount",totale);
            /*
            jsonArray.put(0,o1);
            jsonArray.put(1,o2);
            jsonArray.put(2,o3);
            */
            jsonObject.putOpt("products",jsonArray);
            Log.d("debug",jsonObject.getString("restaurant"));
            Log.d("debug",jsonObject.getString("user"));
           // Log.d("debug",jsonObject.getJSONArray("products").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("risposta",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("risposta",response.toString());
    }


    public class GetOrder extends AsyncTask<Void,Void,Ordine> {

        @Override
        protected Ordine doInBackground(Void... voids) {
            Ordine ordine = AppDatabase.getAppDatabase(CheckoutActivity.this).orderDao().lastOrdine();
            return ordine;
        }

        @Override
        protected void onPostExecute(Ordine ordine) {
            adapter.setOrdine(ordine);
            totale.append(" " + String.valueOf(ordine.calcoloTotale()));
        }
    }
}