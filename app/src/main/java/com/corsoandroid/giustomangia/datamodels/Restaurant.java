package com.corsoandroid.giustomangia.datamodels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrea on 04/02/2019.
 */

@Entity(tableName = "restaurant")
public class Restaurant {

    @ColumnInfo(name = "name")
    private String nome;

    @ColumnInfo(name = "address")
    private String indirizzo;

    @ColumnInfo(name = "min_order")
    private float ordineMinimo;

    @ColumnInfo(name = "url_image")
    private String url;

    @ColumnInfo(name = "restaurant_id")
    private String id;

    @ColumnInfo(name = "products")
    @TypeConverters(ProductConverter.class)
    private List<Product> listaProdottiRistorante;

    public Restaurant(String nome, String indirizzo, float ordineMinimo, String url){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.ordineMinimo=ordineMinimo;
        this.url=url;
        this.id="0";
        listaProdottiRistorante = new ArrayList<>();
    }

    public Restaurant(JSONObject jsonRestoraunt) {
        try {
            this.nome = jsonRestoraunt.getString("name");
            this.indirizzo = jsonRestoraunt.getString("address");
            this.ordineMinimo = (float) jsonRestoraunt.getDouble("min_order");
            this.url = jsonRestoraunt.getString("image_url");
            this.id = jsonRestoraunt.getString("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListaPortateRistorante(ArrayList<Product> listaProdottiRistorante) {
        this.listaProdottiRistorante = listaProdottiRistorante;
    }


    public float getOrdineMinimo() {
        return ordineMinimo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getLogo() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOrdineMinimo(float ordineMinimo) {
        this.ordineMinimo = ordineMinimo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getListaProdottiRistorante() {
        return listaProdottiRistorante;
    }

    public void setListaProdottiRistorante(List<Product> listaProdottiRistorante) {
        this.listaProdottiRistorante = listaProdottiRistorante;
    }
}
