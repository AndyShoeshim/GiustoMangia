package com.corsoandroid.giustomangia.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.datamodels.Restaurant;
import com.corsoandroid.giustomangia.ui.activities.ShopActivity;

import java.util.ArrayList;

/**
 * Created by Andrea on 04/02/2019.
 */

public class RestaurantAdapters extends RecyclerView.Adapter {

    LayoutInflater inflater;
    private ArrayList<Restaurant> data;
    private Context context;
    private static boolean isGrid;

    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    public boolean isGrid() {
        return isGrid;
    }

    public RestaurantAdapters(Context context, ArrayList<Restaurant> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(isGrid)
        {
            view = inflater.inflate(R.layout.item_restorauntgrid,parent,false);
        } else

        view = inflater.inflate(R.layout.item_restoraunt, parent, false);
        return new RestorauntViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RestorauntViewHolder rvh = (RestorauntViewHolder) holder;
        Glide.with(context).load(data.get(position).getLogo()).into(rvh.restorauntImage);
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

        public ImageView restorauntImage;
        public TextView restorauntName;
        public TextView restorauntAddress;
        public TextView restorauntMinCost;

        public RestorauntViewHolder(final View itemView) {

            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, ShopActivity.class);
                    i.putExtra("nomeRistorante",restorauntName.getText().toString());
                    context.startActivity(i);
                }
            });


            restorauntImage = itemView.findViewById(R.id.restorauntImage);
            restorauntName = itemView.findViewById(R.id.restaurantName);
            restorauntAddress = itemView.findViewById(R.id.restaurantIndirizzo);
            restorauntMinCost = itemView.findViewById(R.id.restaurantOrdineMinimo);
        }
    }
}
