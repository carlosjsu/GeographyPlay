package com.udistrital.geographyplay;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class Conocimientos extends AppCompatActivity {
    private Button btnResponder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_conocimiento);
        btnResponder = findViewById(R.id.IniciarCuestionario);
        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Conocimientos.this,Saber.class);
                startActivity(intent);
            }
        });
    }
}