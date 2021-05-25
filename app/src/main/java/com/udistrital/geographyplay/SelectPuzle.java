package com.udistrital.geographyplay;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectPuzle extends AppCompatActivity {

    Button bsur;
    Button bnorte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectpuzle);
        bsur = (Button)findViewById(R.id.btnSur);
        bnorte = (Button)findViewById(R.id.btnNorte);
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
