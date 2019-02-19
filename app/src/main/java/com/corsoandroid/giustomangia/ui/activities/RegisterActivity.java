package com.corsoandroid.giustomangia.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Utilities;
import com.corsoandroid.giustomangia.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrea on 31/01/2019.
 */

public class RegisterActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    LinearLayout layout;
    //EditText email, password, confermaPassword, numTelefono;
    EditText email, password, username, numTelefono;
    Button b;
    boolean darkTheme=false;
    RestController restController;
    private static final String endpoint= "auth/local/register";
    SharedPreferences.Editor spEditor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.testoEmail);
        password=findViewById(R.id.testoPassword);
        //confermaPassword=findViewById(R.id.confermaPassword);
        username=findViewById(R.id.userName);
        numTelefono=findViewById(R.id.numTelefono);
        b=findViewById(R.id.registerButton);
        layout=findViewById(R.id.layoutRegister);
        checkDarkTheme();
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
        restController = new RestController(this);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                abilitaTasto();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                abilitaTasto();
            }
        });


        numTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                abilitaTasto();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registraDati();
            }
        });
    }

    private void abilitaTasto() {
        boolean emailResult = Utilities.checkEmail(this,email);
        boolean passResult = Utilities.checkPass(this,password);
        boolean telResult = Utilities.checkNumTel(this,numTelefono);

        if(emailResult && passResult && telResult) {
            b.setEnabled(true);
            b.setBackgroundColor(getResources().getColor(R.color.colorSecondary,null));
        }
    }

    private void registraDati() {
        String emailUtente = email.getText().toString();
        String userName = username.getText().toString();
        String pass = password.getText().toString();
        //String confPass = confermaPassword.getText().toString();
        restController.postRegisterRequest(endpoint,this,this,userName,emailUtente,pass);
    }

    public void checkDarkTheme(){
        if(getIntent().getBooleanExtra("statoTema",false))
        {
            layout.setBackgroundColor(getResources().getColor(R.color.colorDarkTheme,null));
        } else
            layout.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("registrazione",error.toString());
        Utilities.showToast(this,R.string.registerBad_text);
    }

    @Override
    public void onResponse(String response) {
        Log.i("registrazione",response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            String token = jsonObject.getString("jwt");
            Log.i("tokenRegistrazione",token);
            spEditor.putString("tokenUser",token);
            spEditor.apply();
            Utilities.showToast(this,R.string.registerGood_text);
        } catch (JSONException e) {
            Log.e("errorRegistrazione",e.getMessage());
        }
    }
}
