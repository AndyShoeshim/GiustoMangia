package com.corsoandroid.giustomangia.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrea on 31/01/2019.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>,Response.ErrorListener {

    private static final String endpoint = "auth/local";

    LinearLayout l;
    Button loginButton;
    Button registerButton;
    Switch s;
    EditText emailText, passText;
    boolean darkTheme=false;
    RestController restController;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        emailText = findViewById(R.id.testoEmail);
        passText = findViewById(R.id.testoPassword);
        s = findViewById(R.id.switchColor);
        l=findViewById(R.id.layout); // INIZIALIZZAZIONE DELLE VARIABILI RELATIVE AI COMPONENTI

        sharedPreferences = getSharedPreferences("sharedPref",Context.MODE_PRIVATE);

        restController = new RestController(this);

        if (hasInvitationCode()) {
            registerButton.setVisibility(View.VISIBLE);
        }

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    darkTheme=true;
                    l.setBackgroundColor(getResources().getColor(R.color.colorDarkTheme,null));
                    emailText.setTextColor(getResources().getColor(R.color.colorPrimary,null));
                    passText.setTextColor(getResources().getColor(R.color.colorPrimary,null));
                }
                else {
                    darkTheme=false;
                    l.setBackgroundColor(Color.WHITE);
                    //emailText.setTextColor(getResources().getColor(R.color.colorDarkTheme,null));
                    //passText.setTextColor(getResources().getColor(R.color.colorDarkTheme,null));
                }
            }
        }); // LISTENER DELLO STATO DELLO SWITCH

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginButton) {
            if(doLogin()) {
                restController.postLoginRequest(endpoint,this,this,emailText.getText().toString(),passText.getText().toString());
            }
        } else if(view.getId()==R.id.registerButton) {
            Intent i = new Intent(this,RegisterActivity.class);
            i.putExtra("statoTema",darkTheme); // passaggio della variabile booleana per il dark theme
            startActivity(i); // passaggio all'activity di registrazione dell'utente
        }
    }

    private boolean hasInvitationCode() {
        return true;
    }

    private boolean doLogin() {
       if(Utilities.checkEmail(this,emailText)) {
           if (Utilities.checkPass(this, passText)) {
               Utilities.showToast(this, R.string.loginOk_text);
               return true;
           } else {
               Utilities.showToast(this, R.string.loginPassBad_text);
               return false;
           }
       }
       else {
           Utilities.showToast(this, R.string.loginEmailBad_text);
           return false;
       }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("error",error.toString());
        Utilities.showToast(this,R.string.loginBad_text);
    }

    @Override
    public void onResponse(String response) {
        Log.i("login", response.toCharArray().toString());
        try {
            JSONObject jsonObject = new JSONObject(response);
            String token = jsonObject.getString("jwt");
            spEditor = sharedPreferences.edit();
            spEditor.putString("tokenLogin",token);
            spEditor.apply();
            Utilities.showToast(this, R.string.loginOk_text);
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Utilities.LOGIN_ACTION));
            /*
            setResult(Activity.RESULT_OK);
            finish();*/

        } catch (JSONException e) {
            Log.e("erroreLogin",e.getMessage());
        }
    }
}
