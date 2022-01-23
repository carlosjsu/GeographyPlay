package com.udistrital.geographyplay;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.ui.AppBarConfiguration;

import com.google.firebase.auth.FirebaseAuth;
import com.udistrital.geographyplay.databinding.ActivityInicioBinding;

public class Inicio extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityInicioBinding binding;
    Button Iniciar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Iniciar=(Button)findViewById(R.id.btnIniciar);
        firebaseAuth = FirebaseAuth.getInstance();
        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent homeIntent = new Intent(Inicio.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
                else{
                    Intent homeIntent = new Intent(Inicio.this, login.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}