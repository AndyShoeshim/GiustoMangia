package com.corsoandroid.giustomangia;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.corsoandroid.giustomangia.datamodels.Product;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrea on 31/01/2019.
 */

public  class Utilities {
    // CLASSE NELLA QUALE CI SONO I METODI RIDONDANTI

    public static final String LOGIN_ACTION = "LOGIN_ACTION";
    public static final String LOGOUT_ACTION = "LOGOUT_RECEIVER";

    public static boolean checkEmail(Context c,EditText et) {
        String email = et.getText().toString();
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            //showToast(c,R.string.loginEmailBad_text);
            return false;
    }

    public static boolean checkPass(Context c,EditText et) {
        String pass = et.getText().toString();
        if(!pass.equals("") && pass.length()>6)
            return true;
        else
            //showToast(c,R.string.loginPassBad_text);
            return false;
    }

    public static boolean checkNumTel(Context c,EditText et) {
        String numTelefono = et.getText().toString();
        if(Patterns.PHONE.matcher(numTelefono).matches())
            return true;
        else
            //showToast(c,R.string.cellNumberError_text);
            return false;
    }

    public static void showToast(Context c, int id) {
        Toast.makeText(c, id, Toast.LENGTH_SHORT).show();
    }

    public static JSONObject parseToJSON(Product p){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",p.getNome());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
