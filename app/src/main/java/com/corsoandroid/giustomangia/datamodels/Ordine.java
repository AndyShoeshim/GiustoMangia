package com.corsoandroid.giustomangia.datamodels;

import java.util.ArrayList;

/**
 * Created by Andrea on 11/02/2019.
 */

public class Ordine {
    public  Restaurant restaurant;
    public  ArrayList<Product> productArrayList;
    public  float totale;

    public Ordine(Restaurant restaurant,ArrayList<Product> productArrayList) {
        this.restaurant=restaurant;
        this.productArrayList=productArrayList;

        for(int i = 0;i<productArrayList.size();i++){
            totale=productArrayList.get(i).getCosto();
        }
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
}