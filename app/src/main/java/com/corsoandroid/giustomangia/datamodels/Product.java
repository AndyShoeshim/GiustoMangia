package com.corsoandroid.giustomangia.datamodels;

/**
 * Created by Andrea on 05/02/2019.
 */

public class Product {
    private String nome;
    private float costo;
    public int quantita;

    public Product(String nome, float costo) {
        this.nome=nome;
        this.costo=costo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public float getCosto() {
        return costo;
    }

    public void increaseQt() {
        this.quantita++;
    }

    public void decreaseQt() {
        if(!(this.quantita==0)) {
            this.quantita--;
        }
    }
}
