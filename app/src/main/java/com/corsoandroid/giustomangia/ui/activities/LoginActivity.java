package com.corsoandroid.giustomangia.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Utilities;

/**
 * Created by Andrea on 31/01/2019.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout l;
    Button loginButton;
    Button registerButton;
    Switch s;
    EditText emailText, passText;
    boolean darkTheme=false;

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
                    emailText.setTextColor(getResources().getColor(R.color.colorDarkTheme,null));
                    passText.setTextColor(getResources().getColor(R.color.colorDarkTheme,null));
                }
            }
        }); // LISTENER DELLO STATO DELLO SWITCH

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginButton) {
            if(doLogin()) {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("statoTema",darkTheme);
                startActivity(i);
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
}
