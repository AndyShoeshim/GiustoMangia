package com.corsoandroid.giustomangia.datamodels;

import android.graphics.drawable.Drawable;

/**
 * Created by Andrea on 04/02/2019.
 */

public class Restoraunt {
    private String nome;
    private String indirizzo;
    private float ordineMinimo;
    int logo;

    public Restoraunt(String nome,String indirizzo,float ordineMinimo,int logo){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.ordineMinimo=ordineMinimo;
        this.logo=logo;
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

    public int getLogo() {
        return logo;
    }
}
