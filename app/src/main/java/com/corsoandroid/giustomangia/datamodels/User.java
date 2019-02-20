package com.corsoandroid.giustomangia.datamodels;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrea on 18/02/2019.
 */

public class User {
    private String token;
    private String email;
    private String username;
    private String numCellulare;

    public User(JSONObject jsonObject) {
        try {
            this.token=jsonObject.getString("jwt");
            JSONObject infoUtente=jsonObject.getJSONObject("user");
            this.email = infoUtente.getString("email");
            this.username = infoUtente.getString("username");
        } catch (JSONException e) {
            Log.e("errorUtente","Errore nel parsing del JSON");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getNumCellulare() {
        return numCellulare;
    }

    public String getToken() {
        return token;
    }
}
