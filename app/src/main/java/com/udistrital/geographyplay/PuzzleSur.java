package com.udistrital.geographyplay;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class PuzzleSur extends AppCompatActivity {

    ImageView cuadro1, cuadro2, cuadro3, cuadro4, cuadro5, cuadro6, cuadro7, cuadro8,cuadro9,
            imgColombia, imgVenezuela, imgGuyanas, imgPeru, imgBolivia, imgBrasil, imgChile, imgArgentina,
            imgColombia1, imgVenezuela1, imgGuyanas1, imgPeru1, imgBolivia1, imgBrasil1, imgChile1, imgArgentina1, imgnn, imgnn9;
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlesur);
        cuadro1 = findViewById(R.id.cuadroBlanco1);
        cuadro2 = findViewById(R.id.cuadroBlanco2);
        cuadro3 = findViewById(R.id.cuadroBlanco3);
        cuadro4 = findViewById(R.id.cuadroBlanco4);
        cuadro5 = findViewById(R.id.cuadroBlanco5);
        cuadro6 = findViewById(R.id.cuadroBlanco6);
        cuadro7 = findViewById(R.id.cuadroBlanco7);
        cuadro8 = findViewById(R.id.cuadroBlanco8);
        imgColombia = findViewById(R.id.imgPt1Colombia);
        imgColombia1 = findViewById(R.id.Pt1Colombia);
        imgVenezuela = findViewById(R.id.imgPt2Venezuela);
        imgVenezuela1 =findViewById(R.id.Pt2Venezuela);
        imgGuyanas = findViewById(R.id.imgPt3Guyanas);
        imgGuyanas1= findViewById(R.id.Pt3Guyanas);
        imgPeru = findViewById(R.id.imgPt4Peru);
        imgPeru1 = findViewById(R.id.Pt4Peru);
        imgBolivia = findViewById(R.id.imgPt5Bolivia);
        imgBolivia1 = findViewById(R.id.Pt5Bolivia);
        imgBrasil = findViewById(R.id.imgPt6Brasil);
        imgBrasil1 = findViewById(R.id.Pt6Brasil);
        imgChile = findViewById(R.id.imgPt7Chile);
        imgChile1 = findViewById(R.id.Pt7Chile);
        imgArgentina = findViewById(R.id.imgPt8Argentina);
        imgArgentina1 = findViewById(R.id.Pt8Argentina);
        imgColombia.setOnTouchListener(new ChoiceTouchListener());
        imgVenezuela.setOnTouchListener(new ChoiceTouchListener());
        imgGuyanas.setOnTouchListener(new ChoiceTouchListener());
        imgPeru.setOnTouchListener(new ChoiceTouchListener());
        imgBolivia.setOnTouchListener(new ChoiceTouchListener());
        imgBrasil.setOnTouchListener(new ChoiceTouchListener());
        imgChile.setOnTouchListener(new ChoiceTouchListener());
        imgArgentina.setOnTouchListener(new ChoiceTouchListener());
        cuadro1.setOnDragListener(new ChoiceDragListener());
        cuadro2.setOnDragListener(new ChoiceDragListener());
        cuadro3.setOnDragListener(new ChoiceDragListener());
        cuadro4.setOnDragListener(new ChoiceDragListener());
        cuadro5.setOnDragListener(new ChoiceDragListener());
        cuadro6.setOnDragListener(new ChoiceDragListener());
        cuadro7.setOnDragListener(new ChoiceDragListener());
        cuadro8.setOnDragListener(new ChoiceDragListener());
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;

            }
        }
    }

    public class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView view = (ImageView) event.getLocalState();
                    if(DragEvent.ACTION_DROP != DragEvent.ACTION_DRAG_ENDED) {
                        if (v == cuadro1) {
                            if (view == imgColombia) {
                                cuadro1.setVisibility(View.INVISIBLE);
                                imgColombia1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro2) {
                            if (view == imgVenezuela) {
                                cuadro2.setVisibility(View.INVISIBLE);
                                imgVenezuela1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro3) {
                            if (view == imgGuyanas) {
                                cuadro3.setVisibility(View.INVISIBLE);
                                imgGuyanas1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro4) {
                            if (view == imgPeru) {
                                cuadro4.setVisibility(View.INVISIBLE);
                                imgPeru1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro5) {
                            if (view == imgBolivia) {
                                cuadro5.setVisibility(View.INVISIBLE);
                                imgBolivia1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro6) {
                            if (view == imgBrasil) {
                                cuadro6.setVisibility(View.INVISIBLE);
                                imgBrasil1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        } if (v == cuadro7) {
                            if (view == imgChile) {
                                cuadro7.setVisibility(View.INVISIBLE);
                                imgChile1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }if (v == cuadro8) {
                            if (view == imgArgentina) {
                                cuadro8.setVisibility(View.INVISIBLE);
                                imgArgentina1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                cont++;
                            }
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }
            if(cont==8){
                finalizar();
            }
            return true;
        }
    }

    private void finalizar(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PuzzleSur.this);
        alertDialogBuilder.setMessage("¡FIN DEL JUEGO!\n¡GANASTE!").setCancelable(false)
                .setPositiveButton("Nuevo Intento", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),PuzzleSur.class);
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