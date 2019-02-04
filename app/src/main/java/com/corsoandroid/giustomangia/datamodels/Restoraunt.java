package com.corsoandroid.giustomangia.datamodels;

/**
 * Created by Andrea on 04/02/2019.
 */

public class Restoraunt {
    private String nome;
    private String indirizzo;
    private float ordineMinimo;

    public Restoraunt(String nome,String indirizzo,float ordineMinimo){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.ordineMinimo=ordineMinimo;
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
}
