package com.udistrital.geographyplay;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class ConcentreseSur extends AppCompatActivity {

    TextView tv_p1, tv_p2;
    ImageView iv_11, iv_12, iv_13, iv_14,
              iv_21, iv_22, iv_23, iv_24,
              iv_31, iv_32, iv_33, iv_34,
              iv_41, iv_42, iv_43, iv_44;
    Integer[] cartaArray={101, 102, 103, 104, 105, 106, 107, 108 , 201, 202, 203, 204, 205, 206, 207, 208};
    int imagen101, imagen102, imagen103, imagen104, imagen105, imagen106, imagen107, imagen108,
            imagen201, imagen202, imagen203, imagen204, imagen205, imagen206, imagen207, imagen208;
    int primerCarta, segundaCarta;
    int clickPrimera, clickSegunda;
    int numeroCarta = 1;
    int turno = 1;
    int jugadorPuntos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concentresesur);
        getSupportActionBar().hide();
        tv_p1= (TextView) findViewById(R.id.tv_p1);
        tv_p2= (TextView) findViewById(R.id.tv_p2);

        iv_11= (ImageView) findViewById(R.id.iv_11);
        iv_12= (ImageView) findViewById(R.id.iv_12);
        iv_13= (ImageView) findViewById(R.id.iv_13);
        iv_14= (ImageView) findViewById(R.id.iv_14);
        iv_21= (ImageView) findViewById(R.id.iv_21);
        iv_22= (ImageView) findViewById(R.id.iv_22);
        iv_23= (ImageView) findViewById(R.id.iv_23);
        iv_24= (ImageView) findViewById(R.id.iv_24);
        iv_31= (ImageView) findViewById(R.id.iv_31);
        iv_32= (ImageView) findViewById(R.id.iv_32);
        iv_33= (ImageView) findViewById(R.id.iv_33);
        iv_34= (ImageView) findViewById(R.id.iv_34);
        iv_41= (ImageView) findViewById(R.id.iv_41);
        iv_42= (ImageView) findViewById(R.id.iv_42);
        iv_43= (ImageView) findViewById(R.id.iv_43);
        iv_44= (ImageView) findViewById(R.id.iv_44);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");
        iv_41.setTag("12");
        iv_42.setTag("13");
        iv_43.setTag("14");
        iv_44.setTag("15");
        LlamarCartas();

        Collections.shuffle(Arrays.asList(cartaArray));

        tv_p2.setTextColor(Color.BLACK);

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_11, laCarta);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_12, laCarta);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_13, laCarta);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_14, laCarta);
            }
        });
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_21, laCarta);
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_22, laCarta);
            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_23, laCarta);
            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_24, laCarta);
            }
        });
        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_31, laCarta);
            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_32, laCarta);
            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_33, laCarta);
            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_34, laCarta);
            }
        });
        iv_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_41, laCarta);
            }
        });
        iv_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_42, laCarta);
            }
        });
        iv_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_43, laCarta);
            }
        });
        iv_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int laCarta= Integer.parseInt((String) view.getTag());
                mezclar(iv_44, laCarta);
            }
        });
    }
    private  void mezclar(ImageView iv, int carta){
        if(cartaArray[carta]==101){
            iv.setImageResource(imagen101);
        }else if(cartaArray[carta]==102){
            iv.setImageResource(imagen102);
        }else if(cartaArray[carta]==103){
            iv.setImageResource(imagen103);
        }else if(cartaArray[carta]==104){
            iv.setImageResource(imagen104);
        }else if(cartaArray[carta]==105){
            iv.setImageResource(imagen105);
        }else if(cartaArray[carta]==106){
            iv.setImageResource(imagen106);
        }else if(cartaArray[carta]==107){
            iv.setImageResource(imagen107);
        }else if(cartaArray[carta]==108){
            iv.setImageResource(imagen108);
        }else if(cartaArray[carta]==201){
            iv.setImageResource(imagen201);
        }else if(cartaArray[carta]==202){
            iv.setImageResource(imagen202);
        }else if(cartaArray[carta]==203){
            iv.setImageResource(imagen203);
        }else if(cartaArray[carta]==204){
            iv.setImageResource(imagen204);
        }else if(cartaArray[carta]==205){
            iv.setImageResource(imagen205);
        }else if(cartaArray[carta]==206){
            iv.setImageResource(imagen206);
        }else if(cartaArray[carta]==207){
            iv.setImageResource(imagen207);
        }else if(cartaArray[carta]==208){
            iv.setImageResource(imagen208);
        }

        if(numeroCarta == 1){
            primerCarta=cartaArray[carta];
            if(primerCarta > 200){
                primerCarta=primerCarta - 100;
            }
            numeroCarta = 2;
            clickPrimera = carta;

            iv.setEnabled(false);
        }else if(numeroCarta == 2){
            segundaCarta = cartaArray[carta];
            if(segundaCarta > 200){
                segundaCarta = segundaCarta - 100;
            }
            numeroCarta=1;
            clickSegunda = carta;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);
            iv_41.setEnabled(false);
            iv_42.setEnabled(false);
            iv_43.setEnabled(false);
            iv_44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    comparar();
                }
            }, 500);
        }
    }


    private void comparar(){
        if(primerCarta == segundaCarta){
            if(clickPrimera == 0){
                iv_11.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 11){
                iv_34.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 12){
                iv_41.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 13){
                iv_42.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 14){
                iv_43.setVisibility(View.INVISIBLE);
            }else if(clickPrimera == 15){
                iv_44.setVisibility(View.INVISIBLE);
            }

            if(clickSegunda == 0){
                iv_11.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 11){
                iv_34.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 12){
                iv_41.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 13){
                iv_42.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 14){
                iv_43.setVisibility(View.INVISIBLE);
            }else if(clickSegunda == 15){
                iv_44.setVisibility(View.INVISIBLE);
            }

            if(turno == 1){
                jugadorPuntos++;
                tv_p1.setText("Jugador:"+jugadorPuntos);
            }
        }else {
            iv_11.setImageResource(R.drawable.incognita);
            iv_12.setImageResource(R.drawable.incognita);
            iv_13.setImageResource(R.drawable.incognita);
            iv_14.setImageResource(R.drawable.incognita);
            iv_21.setImageResource(R.drawable.incognita);
            iv_22.setImageResource(R.drawable.incognita);
            iv_23.setImageResource(R.drawable.incognita);
            iv_24.setImageResource(R.drawable.incognita);
            iv_31.setImageResource(R.drawable.incognita);
            iv_32.setImageResource(R.drawable.incognita);
            iv_33.setImageResource(R.drawable.incognita);
            iv_34.setImageResource(R.drawable.incognita);
            iv_41.setImageResource(R.drawable.incognita);
            iv_42.setImageResource(R.drawable.incognita);
            iv_43.setImageResource(R.drawable.incognita);
            iv_44.setImageResource(R.drawable.incognita);

            if(turno == 1){
                turno = 1;
                tv_p1.setTextColor(Color.BLACK);
            }
        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);
        iv_41.setEnabled(true);
        iv_42.setEnabled(true);
        iv_43.setEnabled(true);
        iv_44.setEnabled(true);

        finalizar();
    }
    private void finalizar(){
        if(iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE &&
                iv_41.getVisibility() == View.INVISIBLE &&
                iv_42.getVisibility() == View.INVISIBLE &&
                iv_43.getVisibility() == View.INVISIBLE &&
                iv_44.getVisibility() == View.INVISIBLE){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ConcentreseSur.this);
            alertDialogBuilder.setMessage("¡FIN DEL JUEGO!\n¡GANASTE!").setCancelable(false)
                    .setPositiveButton("Nuevo Intento", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), ConcentreseSur.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    private void LlamarCartas(){
        imagen101=R.drawable.a;
        imagen102=R.drawable.ch;
        imagen103=R.drawable.b;
        imagen104=R.drawable.pgy;
        imagen105=R.drawable.br;
        imagen106=R.drawable.c;
        imagen107=R.drawable.p;
        imagen108=R.drawable.u;
        imagen201=R.drawable.ar;
        imagen202=R.drawable.chi;
        imagen203=R.drawable.bo;
        imagen204=R.drawable.pgy1;
        imagen205=R.drawable.bra;
        imagen206=R.drawable.co;
        imagen207=R.drawable.pa;
        imagen208=R.drawable.ur;
    }
}
