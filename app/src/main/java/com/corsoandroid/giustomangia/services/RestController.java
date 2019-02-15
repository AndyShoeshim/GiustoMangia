package com.corsoandroid.giustomangia.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;

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
}
