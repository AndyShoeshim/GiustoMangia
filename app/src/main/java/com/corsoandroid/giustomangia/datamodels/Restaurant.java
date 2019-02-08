package com.corsoandroid.giustomangia.datamodels;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andrea on 04/02/2019.
 */

public class Restaurant {
    private String nome;
    private String indirizzo;
    private float ordineMinimo;
    private String url;
    private ArrayList<Product> listaProdottiRistorante;

    public Restaurant(String nome, String indirizzo, float ordineMinimo, String url){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.ordineMinimo=ordineMinimo;
        this.url=url;
        listaProdottiRistorante = new ArrayList<>();
    }

    public void setListaPortateRistorante(ArrayList<Product> listaProdottiRistorante) {
        this.listaProdottiRistorante = listaProdottiRistorante;
    }

    public ArrayList<Product> getListaProdottiRistorante() {
        return listaProdottiRistorante;
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
}
