package com.udistrital.geographyplay;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectConcentrese extends AppCompatActivity {

    ImageButton memosur;
    ImageButton memonorte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectconcentrese);

        memosur = (ImageButton)findViewById(R.id.Sur);
        memonorte = (ImageButton)findViewById(R.id.Norte);
        getSupportActionBar().hide();
        memosur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memosur = new Intent(SelectConcentrese.this, ConcentreseSur.class);
                startActivity(memosur);
            }
        });
        memonorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memonorte = new Intent(SelectConcentrese.this, ConcentreseNorte.class);
                startActivity(memonorte);
            }
        });
    }
}
