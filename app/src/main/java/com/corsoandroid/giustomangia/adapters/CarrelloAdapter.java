package com.corsoandroid.giustomangia.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.datamodels.Ordine;

/**
 * Created by Andrea on 11/02/2019.
 */

public class CarrelloAdapter extends RecyclerView.Adapter {

    LayoutInflater layoutInflater;
    Context c;
    Ordine ordine;

    public CarrelloAdapter(Context c, Ordine ordine){
        this.c=c;
        this.ordine=ordine;
        layoutInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = layoutInflater.inflate(R.layout.item_carrello,viewGroup,false);
        return new CarrelloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CarrelloViewHolder cvh = (CarrelloViewHolder) viewHolder;
        cvh.prodotto.setText(ordine.getProductArrayList().get(i).getNome());
        int numProdotto = ordine.getProductArrayList().get(i).getQuantita();
        float costo = ordine.getProductArrayList().get(i).getCosto();
        // fare il calcolo da un'altra parte
        cvh.costoProdotto.setText(String.valueOf(numProdotto*costo));
    }

    @Override
    public int getItemCount() {
        return ordine.getProductArrayList().size();
    }

    public class CarrelloViewHolder extends RecyclerView.ViewHolder {

        TextView prodotto,costoProdotto;
        Button cancellaProdotto;

        public CarrelloViewHolder(@NonNull View itemView) {
            super(itemView);
            prodotto= itemView.findViewById(R.id.prodotto);
            costoProdotto=itemView.findViewById(R.id.costoProdotto);
            cancellaProdotto= itemView.findViewById(R.id.cancellaProdotto);

            cancellaProdotto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ordine.getProductArrayList().remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
