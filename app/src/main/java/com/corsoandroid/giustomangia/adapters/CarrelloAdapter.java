package com.corsoandroid.giustomangia.adapters;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.corsoandroid.giustomangia.OnProductEliminatedListener;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Test;
import com.corsoandroid.giustomangia.datamodels.Ordine;

/**
 * Created by Andrea on 11/02/2019.
 */

public class CarrelloAdapter extends RecyclerView.Adapter implements OnProductEliminatedListener {

    LayoutInflater layoutInflater;
    Context c;
    Ordine ordine;
    OnProductEliminatedListener o;

    public void setO(OnProductEliminatedListener o) {
        this.o = o;
    }

    public OnProductEliminatedListener getO() {
        return o;
    }

    public CarrelloAdapter(Context c, Ordine ordine){
        this.c=c;
        this.ordine=ordine;
        layoutInflater = LayoutInflater.from(c);
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
        notifyDataSetChanged();
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
        cvh.qtprodotto.setText(String.valueOf(ordine.getProductArrayList().get(i).getQuantita()) + "x");
        cvh.prodotto.setText(ordine.getProductArrayList().get(i).getNome());
        cvh.costoProdotto.setText(String.valueOf(ordine.getProductArrayList().get(i).totale));
    }

    @Override
    public int getItemCount() {
        return ordine.getProductArrayList().size();
    }

    @Override
    public void onChangeTotal() {

    }

    public class CarrelloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView qtprodotto, prodotto, costoProdotto;
        ImageButton cancellaProdotto;


        public CarrelloViewHolder(@NonNull View itemView) {
            super(itemView);
            qtprodotto = itemView.findViewById(R.id.qtprodotto);
            prodotto = itemView.findViewById(R.id.prodotto);
            costoProdotto = itemView.findViewById(R.id.costoProdotto);
            cancellaProdotto = itemView.findViewById(R.id.cancellaProdotto);
            cancellaProdotto.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == cancellaProdotto.getId()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
                alertDialog.setTitle("ATTENZIONE");
                alertDialog.setMessage("Sei davvero sicuro di voler eliminare questa parte di ordine?");
                alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ordine.getProductArrayList().remove(getAdapterPosition());
                        getO().onChangeTotal();
                        notifyItemRemoved(getAdapterPosition());
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.create();
                alertDialog.show();
            }
        }
    }
}
