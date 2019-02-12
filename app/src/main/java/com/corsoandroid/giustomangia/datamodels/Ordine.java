package com.corsoandroid.giustomangia.datamodels;

import java.util.ArrayList;

/**
 * Created by Andrea on 11/02/2019.
 */

public class Ordine {
    public  Restaurant restaurant;
    public  ArrayList<Product> productArrayList;
    public float totale;

    public Ordine(Restaurant restaurant,ArrayList<Product> productArrayList) {
        this.restaurant=restaurant;
        this.productArrayList=productArrayList;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public float calcoloTotale() {
        float f=0;
        for(int i=0;i<productArrayList.size();i++){
            f+= productArrayList.get(i).getQuantita()*productArrayList.get(i).getCosto();
        }
        return f;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
