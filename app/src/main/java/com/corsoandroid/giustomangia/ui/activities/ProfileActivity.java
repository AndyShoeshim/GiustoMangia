package com.corsoandroid.giustomangia.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.corsoandroid.giustomangia.R;
import com.corsoandroid.giustomangia.Utilities;

/**
 * Created by Andrea on 19/02/2019.
 */

public class ProfileActivity extends AppCompatActivity {

    TextView emailBox,usernameBox,telefonoBox;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences s = getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = s.edit();
                editor.putString("tokenLogin",null);
                editor.apply();
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(new Intent(Utilities.LOGOUT_ACTION));
            }
        });
    }

    public void initialize() {
        emailBox = findViewById(R.id.emailUtenteProfilo);
        usernameBox = findViewById(R.id.nomeUtenteProfilo);
        telefonoBox = findViewById(R.id.numTelefonoUtenteProfilo);
        button = findViewById(R.id.logoutButton);
    }
}
