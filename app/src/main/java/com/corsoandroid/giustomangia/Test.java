package com.corsoandroid.giustomangia;

import com.corsoandroid.giustomangia.datamodels.Ordine;
import com.corsoandroid.giustomangia.datamodels.Product;
import com.corsoandroid.giustomangia.datamodels.Restaurant;

import java.util.ArrayList;

/**
 * Created by Andrea on 08/02/2019.
 */

public class Test {




    public static ArrayList<Product> creaPortate() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Product p1 = new Product("Whopper",5f);
        Product p2 = new Product("Chicken Bacon", 8f);
        Product p4 = new Product("Patatine", 1f);
        Product p5 = new Product("Insalata", 0.5f);
        Product p6 = new Product("Coca-cola", 1f);
        Product p7 = new Product("Cheeseburger", 3f);
        Product p8 = new Product("Chicken Royale", 4.50f);
        Product p9 = new Product("Chicken Wings", 1.50f);
        Product p10 = new Product("Waffles", 2.50f);
        Product p11 = new Product("Gelato", 1.20f);
        Product p12 = new Product("Kid Menu", 4.00f);
        Product p13 = new Product("Angry Whooper", 7.50f);
        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p4);
        productArrayList.add(p5);
        productArrayList.add(p6);
        productArrayList.add(p7);
        productArrayList.add(p8);
        productArrayList.add(p9);
        productArrayList.add(p10);
        productArrayList.add(p11);
        productArrayList.add(p7);
        productArrayList.add(p8);
        productArrayList.add(p9);
        productArrayList.add(p10);
        productArrayList.add(p11);
        productArrayList.add(p12);
        productArrayList.add(p13);
        return productArrayList;
    }

    public static ArrayList<Restaurant> getData() {
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
        Restaurant r1 = new Restaurant("Burger King ", "Via Tiburtina 58 ", 10.00f, "https://banner2.kisspng.com/20180414/jce/kisspng-hamburger-burger-king-whopper-fast-food-burger-king-5ad1e6bf9fe231.7333694715237055356549.jpg");
        Restaurant r2 = new Restaurant("McDonald's ", "Via Pietralata 666 ", 8.00f, "https://diylogodesigns.com/wp-content/uploads/2016/04/Mcdonalds-logo-png-Transparent-768x538.png");
        Restaurant r3 = new Restaurant("Tira e molla ", "Via Enna 4 ", 10.00f, "https://tiraemollaroma.it/wp-content/uploads/2017/04/tiraemolla_nero.png");
        restaurantArrayList.add(r1);
        restaurantArrayList.add(r2);
        restaurantArrayList.add(r3);

        return restaurantArrayList;
    }

    public static Ordine getOrdine() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Product p1 = new Product("Whopper",5f);
        Product p2 = new Product("Chicken Bacon", 8f);
        Product p3 = new Product("Patatine", 1f);
        Product p4 = new Product("Insalata", 0.5f);
        Product p5 = new Product("Coca-cola", 1f);
        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p3);
        productArrayList.add(p4);
        productArrayList.add(p5);

        Restaurant r = getData().get(1);

        Ordine ordine = new Ordine(r,productArrayList);

        return ordine;
    }
}
