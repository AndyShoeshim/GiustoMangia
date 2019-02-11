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
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.datamodels.Product;

import java.util.ArrayList;

/**
 * Created by Andrea on 07/02/2019.
 */

public class MenuAdapter extends RecyclerView.Adapter {

    LayoutInflater layoutInflater;
    Context c;
    ArrayList<Product> listPortate;

    onQuantityChangeListener onQuantityChangeListener;

    public void setOnQuantityChangeListener(MenuAdapter.onQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public MenuAdapter.onQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public MenuAdapter(Context c, ArrayList<Product> listPortate) {
        this.c = c;
        this.layoutInflater = LayoutInflater.from(c);
        this.listPortate = listPortate;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        view = layoutInflater.inflate(R.layout.item_menu, viewGroup, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuViewHolder mvh = (MenuViewHolder) viewHolder;
        mvh.nomePortata.setText(listPortate.get(i).getNome());
        mvh.costoPortata.setText(String.valueOf(listPortate.get(i).getCosto()));
        mvh.qtPortata.setText(String.valueOf(listPortate.get(i).getQuantita()));
    }

    @Override
    public int getItemCount() {
        return listPortate.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nomePortata, costoPortata, qtPortata;
        public Button removePortata, addPortata;


        public MenuViewHolder(View itemView) {
            super(itemView);
            nomePortata = itemView.findViewById(R.id.portata);
            costoPortata = itemView.findViewById(R.id.costoPortata);
            qtPortata = itemView.findViewById(R.id.numPortate);
            removePortata = itemView.findViewById(R.id.minusNum);
            addPortata = itemView.findViewById(R.id.addNum);

            removePortata.setOnClickListener(this);
            addPortata.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Product product = listPortate.get(getAdapterPosition());

            if (v.getId() == R.id.minusNum) {
                if (qtPortata.getText().toString().equals("0")) {
                    Utilities.showToast(c, R.string.erroreNumPortata);
                    return;
                } else {
                    product.decreaseQt();
                    notifyItemChanged(getAdapterPosition());
                    onQuantityChangeListener.onChange((product.getCosto()*-1));
                    qtPortata.setText(String.valueOf(product.quantita));
                }
            } else if (v.getId() == R.id.addNum) {
                product.increaseQt();
                notifyItemChanged(getAdapterPosition());
                onQuantityChangeListener.onChange(product.getCosto());
                qtPortata.setText(String.valueOf(product.quantita));
            }
        }
    }

    public interface onQuantityChangeListener {
        void onChange(float price);
    }
}
