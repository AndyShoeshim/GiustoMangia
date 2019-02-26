package com.corsoandroid.giustomangia.datamodels;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrea on 25/02/2019.
 */

public class ProductConverter {

    @TypeConverter
    public static String fromList(List<Product> p){
        Gson json = new Gson();
        String s = json.toJson(p);
        return s;
    }

        @TypeConverter
        public static List<Product> fromString(String list) {
            Type listType = new TypeToken<List<Product>>(){}.getType();
            Gson gson = new Gson();
            List<Product> json = gson.fromJson(list,listType);
            return json;
    }
}
