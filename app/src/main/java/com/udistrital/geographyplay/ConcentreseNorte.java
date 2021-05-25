package com.udistrital.geographyplay;

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

public class ConcentreseNorte extends AppCompatActivity {

    TextView t_p1, t_p2;
    ImageView in_11, in_12, in_13, in_14,
              in_21, in_22, in_23, in_24,
              in_31, in_32, in_33, in_34,
              in_41, in_42, in_43, in_44;
    //areglo de imagenes
    Integer [] arrayCarta = {101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208};
    //imagenes actuales
    int img101, img102, img103, img104, img105, img106, img107, img108,
            img201, img202, img203, img204, img205, img206, img207, img208;
    int carta1, carta2;
    int clickcarta1, clickcarta2;
    int numCarta = 1;
    int turn = 1;
    int ptosJugador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concentrese_norte);

        t_p1 = (TextView) findViewById(R.id.t_p1);
        t_p2 = (TextView) findViewById(R.id.t_p2);

        in_11 = (ImageView) findViewById(R.id.in_11);
        in_12 = (ImageView) findViewById(R.id.in_12);
        in_13 = (ImageView) findViewById(R.id.in_13);
        in_14 = (ImageView) findViewById(R.id.in_14);
        in_21 = (ImageView) findViewById(R.id.in_21);
        in_22 = (ImageView) findViewById(R.id.in_22);
        in_23 = (ImageView) findViewById(R.id.in_23);
        in_24 = (ImageView) findViewById(R.id.in_24);
        in_31 = (ImageView) findViewById(R.id.in_31);
        in_32 = (ImageView) findViewById(R.id.in_32);
        in_33 = (ImageView) findViewById(R.id.in_33);
        in_34 = (ImageView) findViewById(R.id.in_34);
        in_41 = (ImageView) findViewById(R.id.in_41);
        in_42 = (ImageView) findViewById(R.id.in_42);
        in_43 = (ImageView) findViewById(R.id.in_43);
        in_44 = (ImageView) findViewById(R.id.in_44);

        in_11.setTag("0");
        in_12.setTag("1");
        in_13.setTag("2");
        in_14.setTag("3");
        in_21.setTag("4");
        in_22.setTag("5");
        in_23.setTag("6");
        in_24.setTag("7");
        in_31.setTag("8");
        in_32.setTag("9");
        in_33.setTag("10");
        in_34.setTag("11");
        in_41.setTag("12");
        in_42.setTag("13");
        in_43.setTag("14");
        in_44.setTag("15");

        cargarCartas();

        Collections.shuffle(Arrays.asList(arrayCarta));
        t_p2.setTextColor(Color.BLACK);

        in_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_11, carta);
            }
        });
        in_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_12, carta);
            }
        });
        in_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_13, carta);
            }
        });
        in_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_14, carta);
            }
        });
        in_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_21, carta);
            }
        });
        in_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_22, carta);
            }
        });
        in_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_23, carta);
            }
        });
        in_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_24, carta);
            }
        });
        in_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_31, carta);
            }
        });
        in_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_32, carta);
            }
        });
        in_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_33, carta);
            }
        });
        in_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_34, carta);
            }
        });
        in_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_41, carta);
            }
        });
        in_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_42, carta);
            }
        });
        in_43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_43, carta);
            }
        });
        in_44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carta = Integer.parseInt((String) view.getTag());
                revolver(in_44, carta);
            }
        });
    }

    private void revolver(ImageView in, int cartax){
        if(arrayCarta[cartax] == 101){
            in.setImageResource(img101);
        }else if(arrayCarta[cartax] == 102){
            in.setImageResource(img102);
        }else if(arrayCarta[cartax] == 103){
            in.setImageResource(img103);
        }else if(arrayCarta[cartax] == 104){
            in.setImageResource(img104);
        }else if(arrayCarta[cartax] == 105){
            in.setImageResource(img105);
        }else if(arrayCarta[cartax] == 106){
            in.setImageResource(img106);
        }else if(arrayCarta[cartax] == 107){
            in.setImageResource(img107);
        }else if(arrayCarta[cartax] == 108){
            in.setImageResource(img108);
        }else if(arrayCarta[cartax] == 201){
            in.setImageResource(img201);
        }else if(arrayCarta[cartax] == 202){
            in.setImageResource(img202);
        }else if(arrayCarta[cartax] == 203){
            in.setImageResource(img203);
        }else if(arrayCarta[cartax] == 204){
            in.setImageResource(img204);
        }else if(arrayCarta[cartax] == 205){
            in.setImageResource(img205);
        }else if(arrayCarta[cartax] == 206){
            in.setImageResource(img206);
        }else if(arrayCarta[cartax] == 207){
            in.setImageResource(img207);
        }else if(arrayCarta[cartax] == 208){
            in.setImageResource(img208);
        }

        if(numCarta == 1){
            carta1 = arrayCarta[cartax];
            if(carta1 > 200){
                carta1 = carta1 - 100;
            }
            numCarta = 2;
            clickcarta1=cartax;

            in.setEnabled(false);
        }else if(numCarta == 2){
            carta2 = arrayCarta[cartax];
            if(carta2 > 200){
                carta2 = carta2 - 100;
            }
            numCarta = 1;
            clickcarta2 = cartax;

            in_11.setEnabled(false);
            in_12.setEnabled(false);
            in_13.setEnabled(false);
            in_14.setEnabled(false);
            in_21.setEnabled(false);
            in_22.setEnabled(false);
            in_23.setEnabled(false);
            in_24.setEnabled(false);
            in_31.setEnabled(false);
            in_32.setEnabled(false);
            in_33.setEnabled(false);
            in_34.setEnabled(false);
            in_41.setEnabled(false);
            in_42.setEnabled(false);
            in_43.setEnabled(false);
            in_44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compararC();
                }
            }, 500);
        }
    }

    private void compararC(){
        if(carta1 == carta2){
            if(clickcarta1 == 0){
                in_11.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 1){
                in_12.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 2){
                in_13.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 3){
                in_14.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 4){
                in_21.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 5){
                in_22.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 6){
                in_23.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 7){
                in_24.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 8){
                in_31.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 9){
                in_32.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 10){
                in_33.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 11){
                in_34.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 12){
                in_41.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 13){
                in_42.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 14){
                in_43.setVisibility(View.INVISIBLE);
            }else if(clickcarta1 == 15){
                in_44.setVisibility(View.INVISIBLE);
            }

            if(clickcarta2 == 0){
                in_11.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 1){
                in_12.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 2){
                in_13.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 3){
                in_14.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 4){
                in_21.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 5){
                in_22.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 6){
                in_23.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 7){
                in_24.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 8){
                in_31.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 9){
                in_32.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 10){
                in_33.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 11){
                in_34.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 12){
                in_41.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 13){
                in_42.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 14){
                in_43.setVisibility(View.INVISIBLE);
            }else if(clickcarta2 == 15){
                in_44.setVisibility(View.INVISIBLE);
            }

            if(turn == 1) {
                ptosJugador++;
                t_p1.setText("Jugador: " + ptosJugador);
            }
            }else {
                in_11.setImageResource(R.drawable.incognita);
                in_12.setImageResource(R.drawable.incognita);
                in_13.setImageResource(R.drawable.incognita);
                in_14.setImageResource(R.drawable.incognita);
                in_21.setImageResource(R.drawable.incognita);
                in_22.setImageResource(R.drawable.incognita);
                in_23.setImageResource(R.drawable.incognita);
                in_24.setImageResource(R.drawable.incognita);
                in_31.setImageResource(R.drawable.incognita);
                in_32.setImageResource(R.drawable.incognita);
                in_33.setImageResource(R.drawable.incognita);
                in_34.setImageResource(R.drawable.incognita);
                in_41.setImageResource(R.drawable.incognita);
                in_42.setImageResource(R.drawable.incognita);
                in_43.setImageResource(R.drawable.incognita);
                in_44.setImageResource(R.drawable.incognita);


        }
        in_11.setEnabled(true);
        in_12.setEnabled(true);
        in_13.setEnabled(true);
        in_14.setEnabled(true);
        in_21.setEnabled(true);
        in_22.setEnabled(true);
        in_23.setEnabled(true);
        in_24.setEnabled(true);
        in_31.setEnabled(true);
        in_32.setEnabled(true);
        in_33.setEnabled(true);
        in_34.setEnabled(true);
        in_41.setEnabled(true);
        in_42.setEnabled(true);
        in_43.setEnabled(true);
        in_44.setEnabled(true);

        fin();
    }

    private void fin(){
        if (in_11.getVisibility() == View.INVISIBLE &&
                in_12.getVisibility() == View.INVISIBLE &&
                in_13.getVisibility() == View.INVISIBLE &&
                in_14.getVisibility() == View.INVISIBLE &&
                in_21.getVisibility() == View.INVISIBLE &&
                in_22.getVisibility() == View.INVISIBLE &&
                in_23.getVisibility() == View.INVISIBLE &&
                in_24.getVisibility() == View.INVISIBLE &&
                in_31.getVisibility() == View.INVISIBLE &&
                in_32.getVisibility() == View.INVISIBLE &&
                in_33.getVisibility() == View.INVISIBLE &&
                in_34.getVisibility() == View.INVISIBLE &&
                in_41.getVisibility() == View.INVISIBLE &&
                in_42.getVisibility() == View.INVISIBLE &&
                in_43.getVisibility() == View.INVISIBLE &&
                in_44.getVisibility() == View.INVISIBLE){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ConcentreseNorte.this);
            alertDialogBuilder.setMessage("¡Fin del Juego!\n ¡GANASTE!").setCancelable(false)
                    .setPositiveButton("Nuevo Intento", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),ConcentreseNorte.class);
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

    private void cargarCartas(){
        img101 = R.drawable.canada;
        img102 = R.drawable.eeuu;
        img103 = R.drawable.cuba;
        img104 = R.drawable.jm;
        img105 = R.drawable.salvador;
        img106 = R.drawable.guatemala;
        img107 = R.drawable.m;
        img108 = R.drawable.costrica;

        img201 = R.drawable.canada1;
        img202 = R.drawable.eeuu1;
        img203 = R.drawable.cuba1;
        img204 = R.drawable.jm1;
        img205 = R.drawable.salvador1;
        img206 = R.drawable.guatemala1;
        img207 = R.drawable.me;
        img208 = R.drawable.costrica1;
    }
}
