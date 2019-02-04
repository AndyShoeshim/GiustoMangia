package com.corsoandroid.giustomangia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.datamodels.Restoraunt;

import java.util.ArrayList;

/**
 * Created by Andrea on 04/02/2019.
 */

public class RestorauntAdapters extends RecyclerView.Adapter {

    LayoutInflater inflater;
    private ArrayList<Restoraunt> data;

    public RestorauntAdapters (Context context, ArrayList<Restoraunt> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_restoraunt,parent,false);
        return new RestorauntViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RestorauntViewHolder rvh = (RestorauntViewHolder) holder;
        rvh.restorauntName.setText(data.get(position).getNome());
        rvh.restorauntAddress.setText(data.get(position).getIndirizzo());
        String ordineMin = String.valueOf(data.get(position).getOrdineMinimo());
        rvh.restorauntMinCost.append(" " + ordineMin);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RestorauntViewHolder extends RecyclerView.ViewHolder {
        public TextView restorauntName;
        public TextView restorauntAddress;
        public TextView restorauntMinCost;

        public RestorauntViewHolder(View itemView) {
            super(itemView);
            restorauntName = itemView.findViewById(R.id.restaurantName);
            restorauntAddress = itemView.findViewById(R.id.restaurantIndirizzo);
            restorauntMinCost = itemView.findViewById(R.id.restaurantOrdineMinimo);
        }
    }
}
