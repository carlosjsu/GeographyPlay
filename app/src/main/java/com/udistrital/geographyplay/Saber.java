package com.udistrital.geographyplay;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Saber extends AppCompatActivity {

    final String[] Preguntas = {"¿Cuál es la capital de Guatemala?;Escuintla;*Ciudad de Guatemala;Puerto Barrios;Jutiapa",
            "¿Qué país tiene un águila en su bandera?;Guyana;Haití;Surinam;*México",
            "¿Qué país posee una extensión de 756.102 kilómetros cuadrados?;Canadá;Costa Rica;*Chile;Uruguay",
            "¿Qué país de américa del sur tiene un sol en su bandera?;México;*Argentina;Surinam;Ninguna de las anteriores",
            "Bahamas posee una cantidad de habitantes de:;*391.232 personas;301.000 personas;12 millones de personas;3912 personas ;1 millon de personas",
            "¿Cuál es la capital de Brasil?;Bogotá;Honduras;Sao pablo;*Brasilia",
            "¿Cuál es el segundo idioma más hablado en Canadá?;Español;Portugués;*Frances;Inglés",
            "Ecuador tiene su __ en su bandera;Planeta;*Escudo;Capital;No tiene nada en la bandera",
            "¿Qué país posee en su bandera amarillo, azul y rojo?;*Colombia;Brasil;Panamá;Haití",
            "¿En cuál país se habla español?;Santa Lucía;*Perú;Trinidad y Tobago;Jamaica",
            "¿Cuantos continentes hay en la tierra?;Seis;Cuatro;*Cinco;Tres"};
    private int ids_Respuestas[]={
            R.id.Respuesta1,R.id.Respuesta2,R.id.Respuesta3,R.id.Respuesta4
    };
    private int Repe[]={15,15,85,98,54,69,25,50};
    private int respuesta_correcta;
    private int Total_Preguntas=0;
    private int pregunta_actual;
    private TextView Pregunta;
    private TextView NPregunta;
    private RadioGroup grupo;
    private Button btnResponder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saber);

        Pregunta =(TextView) findViewById(R.id.Text_Pregunta);
        grupo= (RadioGroup) findViewById(R.id.grupo) ;
        btnResponder =(Button) findViewById(R.id.btn_Responder);
        NPregunta =(TextView) findViewById(R.id.Np);
        Random generadorAleatorios = new Random();
        final int numeroAleatorio = 0+generadorAleatorios.nextInt(11);
        pregunta_actual= numeroAleatorio;
        Total_Preguntas++;
        Mostrar_Preguntas();

        Repe[0]=numeroAleatorio;

        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = grupo.getCheckedRadioButtonId();
                int respuesta = -1;
                for (int i = 0; i < ids_Respuestas.length; i++) {
                    if (ids_Respuestas[i] == id) {
                        respuesta = i;
                    }
                }
                if (respuesta == respuesta_correcta) {
                Toast.makeText(Saber.this, "Muy bien!! Respuesta Correcta", Toast.LENGTH_SHORT).show();
                Random generadorAleatorios = new Random();

                if(Total_Preguntas <6){
                    int numeroAleatorio = 0+generadorAleatorios.nextInt(11);
                    if(Repetidas(numeroAleatorio)==1){
                        Total_Preguntas++;
                        pregunta_actual= numeroAleatorio;
                        Repe[Total_Preguntas]=numeroAleatorio;
                        Mostrar_Preguntas();
                    }else{
                        int numeroAleatorio1 = 0+generadorAleatorios.nextInt(11);
                        if(Repetidas(numeroAleatorio1)==1){
                            Total_Preguntas++;
                            Repe[Total_Preguntas]=numeroAleatorio1;
                            pregunta_actual= numeroAleatorio1;
                            Mostrar_Preguntas();
                        }else{
                            int numeroAleatorio2 = 0+generadorAleatorios.nextInt(11);
                            if(Repetidas(numeroAleatorio2)==1){
                                Total_Preguntas++;
                                Repe[Total_Preguntas]=numeroAleatorio2;
                                pregunta_actual= numeroAleatorio2;
                                Mostrar_Preguntas();
                            }
                        }
                    }


                }else{
                    Toast.makeText(Saber.this, "Felicidades Superaste el Modulo de Saber!!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            } else {
                Toast.makeText(Saber.this, "Respuesta incorrecta, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
            }

        }
        });
    }

    private void Mostrar_Preguntas() {
        String P= Preguntas[pregunta_actual];
        String[] partes= P.split(";");
        grupo.clearCheck();
        Pregunta.setText(partes[0]);
        NPregunta.setText("Pregunta: "+Total_Preguntas);
        for(int i=0; i<ids_Respuestas.length; i++){
            RadioButton rb=(RadioButton) findViewById(ids_Respuestas[i]);
            String  respuesta =partes[i+1];
            if(respuesta.charAt(0)=='*'){
                respuesta_correcta = i;
                respuesta = respuesta.substring(1);
            }
            rb.setText(respuesta);
        }
        if(Total_Preguntas==6){
            btnResponder.setText("Finalizar Cuestionario");
        }
    }
    private int Repetidas(int numero) {
        for (int i = 0; i < Repe.length; i++) {
            if (Repe[i] == numero) {
               return 0;
            }
        }
        return 1;
    }
}
