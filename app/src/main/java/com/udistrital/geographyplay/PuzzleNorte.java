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

public class PuzzleNorte extends AppCompatActivity {

    ImageView c1, c2, c4, c5, c6, c7, c8, c9,
            img1, img2, img4, img5, img6, img7, img8, img9,
            imgP1, imgP2, imgP4, imgP5, imgP6, imgP7, imgP8, imgP9;
    int acum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlenorte);
        c1 = findViewById(R.id.cBlanco1);
        c2 = findViewById(R.id.cBlanco2);
        c4 = findViewById(R.id.cBlanco4);
        c5 = findViewById(R.id.cBlanco5);
        c6 = findViewById(R.id.cBlanco6);
        c7 = findViewById(R.id.cBlanco7);
        c8 = findViewById(R.id.cBlanco8);
        c9 = findViewById(R.id.cBlanco9);
        img1 = findViewById(R.id.imgPt1);
        imgP1 = findViewById(R.id.Pt1);
        img2 = findViewById(R.id.imgPt2);
        imgP2 =findViewById(R.id.Pt2);
        img4 = findViewById(R.id.imgPt4);
        imgP4 = findViewById(R.id.Pt4);
        img5 = findViewById(R.id.imgPt5);
        imgP5 = findViewById(R.id.Pt5);
        img6 = findViewById(R.id.imgPt6);
        imgP6 = findViewById(R.id.Pt6);
        img7 = findViewById(R.id.imgPt7);
        imgP7 = findViewById(R.id.Pt7);
        img8 = findViewById(R.id.imgPt8);
        imgP8 = findViewById(R.id.Pt8);
        img9 = findViewById(R.id.imgPt9);
        imgP9 = findViewById(R.id.Pt9);
        img1.setOnTouchListener(new ChoiceTouchListener());
        img2.setOnTouchListener(new ChoiceTouchListener());
        img4.setOnTouchListener(new ChoiceTouchListener());
        img5.setOnTouchListener(new ChoiceTouchListener());
        img6.setOnTouchListener(new ChoiceTouchListener());
        img7.setOnTouchListener(new ChoiceTouchListener());
        img8.setOnTouchListener(new ChoiceTouchListener());
        img9.setOnTouchListener(new ChoiceTouchListener());
        c1.setOnDragListener(new ChoiceDragListener());
        c2.setOnDragListener(new ChoiceDragListener());
        c4.setOnDragListener(new ChoiceDragListener());
        c5.setOnDragListener(new ChoiceDragListener());
        c6.setOnDragListener(new ChoiceDragListener());
        c7.setOnDragListener(new ChoiceDragListener());
        c8.setOnDragListener(new ChoiceDragListener());
        c9.setOnDragListener(new ChoiceDragListener());
        getSupportActionBar().hide();
    }

    private final class ChoiceTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }else
            return false;
        }
    }

    public class ChoiceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED://1
                    break;
                case DragEvent.ACTION_DRAG_ENTERED://
                    break;
                case DragEvent.ACTION_DRAG_EXITED://
                    break;
                case DragEvent.ACTION_DROP:
                    ImageView view = (ImageView) event.getLocalState();
                    if (DragEvent.ACTION_DROP != DragEvent.ACTION_DRAG_ENDED) {
                        if (v == c1) {
                            if (view == img1) {
                                c1.setVisibility(View.INVISIBLE);
                                imgP1.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c2) {
                            if (view == img2) {
                                c2.setVisibility(View.INVISIBLE);
                                imgP2.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c4) {
                            if (view == img4) {
                                c4.setVisibility(View.INVISIBLE);
                                imgP4.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c5) {
                            if (view == img5) {
                                c5.setVisibility(View.INVISIBLE);
                                imgP5.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c6) {
                            if (view == img6) {
                                c6.setVisibility(View.INVISIBLE);
                                imgP6.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c7) {
                            if (view == img7) {
                                c7.setVisibility(View.INVISIBLE);
                                imgP7.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c8) {
                            if (view == img8) {
                                c8.setVisibility(View.INVISIBLE);
                                imgP8.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                        }if (v == c9) {
                            if (view == img9) {
                                c9.setVisibility(View.INVISIBLE);
                                imgP9.setVisibility(View.VISIBLE);
                                ((ImageView) view).setImageDrawable(null);
                                acum++;
                            }
                    }
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            }
            if(acum==8){
                finalizarN();
            }
            return true;
        }
    }

    private void finalizarN(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PuzzleNorte.this);
        alertDialogBuilder.setMessage("¡FIN DEL JUEGO!\n¡GANASTE!").setCancelable(false)
                .setPositiveButton("Nuevo Intento", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),PuzzleNorte.class);
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
