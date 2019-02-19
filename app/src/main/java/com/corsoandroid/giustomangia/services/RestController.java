package com.corsoandroid.giustomangia.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.corsoandroid.giustomangia.Utilities;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrea on 14/02/2019.
 */

public class RestController {

    private final static String BASE_URL = "http://138.68.86.70";
    private final String VERSION = "/";
    private RequestQueue queue;

    public RestController(Context context) {
        queue = Volley.newRequestQueue(context);
    }


    public void getRequest(String endpoint, Response.Listener<String> success, Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                BASE_URL.concat(VERSION).concat(endpoint),success,error);
        queue.add(stringRequest);
    }


    public void postRegisterRequest (String endpoint, Response.Listener<String> success, Response.ErrorListener error, final String username, final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,BASE_URL.concat(VERSION).concat(endpoint),success,error)

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };

        queue.add(stringRequest);
     }

     public void postLoginRequest (String endpoint, Response.Listener<String> success, Response.ErrorListener error, final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,BASE_URL.concat(VERSION).concat(endpoint),success,error) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("identifier",username);
                params.put("password",password);

                return params;
            }
        };

        queue.add(stringRequest);
     }
}
