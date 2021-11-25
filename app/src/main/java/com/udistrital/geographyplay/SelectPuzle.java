package com.udistrital.geographyplay;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectPuzle extends AppCompatActivity {

    ImageButton bsur;
    ImageButton bnorte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectpuzle);
        bsur = (ImageButton)findViewById(R.id.btnSur);
        bnorte = (ImageButton)findViewById(R.id.btnNorte);
        bsur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bsur = new Intent(SelectPuzle.this, PuzzleSur.class);
                startActivity(bsur);
            }
        });
        bnorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bnorte = new Intent(SelectPuzle.this, PuzzleNorte.class);
                startActivity(bnorte);
            }
        });
    }
}
