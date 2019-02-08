package com.corsoandroid.giustomangia.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Utilities;

/**
 * Created by Andrea on 31/01/2019.
 */

public class RegisterActivity extends AppCompatActivity {

    LinearLayout layout;
    EditText email, password, confermaPassword, numTelefono;
    Button b;
    boolean darkTheme=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.testoEmail);
        password=findViewById(R.id.testoPassword);
        confermaPassword=findViewById(R.id.confermaPassword);
        numTelefono=findViewById(R.id.numTelefono);
        b=findViewById(R.id.registerButton);
        layout=findViewById(R.id.layoutRegister);
        checkDarkTheme();


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

        if(emailResult && passResult && telResult)
            b.setEnabled(true);
    }

    private void registraDati() {
        String pass = password.getText().toString();
        String confPass = confermaPassword.getText().toString();

        if(pass.equals(confPass))
            Utilities.showToast(this,R.string.registerGood_text);
        else
            Utilities.showToast(this,R.string.registerBad_text);
    }

    public void checkDarkTheme(){
        if(getIntent().getBooleanExtra("statoTema",false))
        {
            layout.setBackgroundColor(getResources().getColor(R.color.colorDarkTheme,null));
        } else
            layout.setBackgroundColor(Color.WHITE);
    }
}
