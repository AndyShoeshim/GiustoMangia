package com.corsoandroid.giustomangia.datamodels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrea on 11/02/2019.
 */

@Entity (tableName = "ordine")
public class Ordine {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    public  Restaurant restaurant;

    @ColumnInfo(name = "product")
    @TypeConverters(ProductConverter.class)
    private List<Product> productArrayList;

    @ColumnInfo(name = "total")
    public float totale;

    public Ordine(){}


    public Ordine(Restaurant restaurant,ArrayList<Product> productArrayList) {
        this.restaurant=restaurant;
        this.productArrayList=productArrayList;
    }

    public List<Product> getProductArrayList() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setProductArrayList(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }
}
