package com.udistrital.geographyplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Conexion extends AppCompatActivity {

    private Button botonEmparejar, botonEscuchar;
    private BluetoothAdapter bluetoothAdapter;
    private Intent intentoHabilitar;
    private int codigoHabilitado;
    private ListView listaDispositivos;
    private TextView status;
    private BluetoothDevice[] btArray;
    private EnviarRecibir enviarRecibir;
    static final int ESTADO_ESCUCHANDO = 1;
    static final int ESTADO_CONECTANDO = 2;
    static final int ESTADO_CONECTADO = 3;
    static final int ESTADO_CONEXION_FALLIDA = 4;
    static final int ESTADO_MENSAJE_RECIBIDO = 5;
    private Boolean bandera = true;
    private CountDownTimer countTimer;
    private long timeInMillis = 10000;
    private FirebaseFirestore mDataBase;
    public static int MILISEGUNDOS_ESPERA = 2000;

    private String[] preguntas = new String[20];
    private int ids_Respuestas[] = {
            R.id.Respuesta1, R.id.Respuesta2, R.id.Respuesta3, R.id.Respuesta4
    };
    private int Repe[] = {23, 34, 85, 98, 54, 69, 25, 50};
    private int respuesta_correcta;
    private int Total_Preguntas = 0;
    private int pregunta_actual;
    private TextView Pregunta;
    private TextView NPregunta;
    private TextView time;
    private TextView puntaje;
    private TextView textPuntaje;
    private RadioGroup grupo;
    private View rule;
    private Boolean banPre = true;
    int BLUETOOTH_HABILITADO = 1;
    private static final String APP_NAME = "GeographyPlayOnline";
    private static final UUID M_UIID = UUID.fromString("efb6c78c-7c45-11ec-90d6-0242ac120003");
    private int totalScore = 0;
    private int scoreQuestion = 0;
    private int timeQuestion = 0;
    private int respuesta = -1;
    private FirebaseAuth mAuth;
    private String user = "";
    private String userConec = "";
    private int totalScoreConec = 0;

    //Generador uuid random UUID.randomUUID().toString()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);
        botonEmparejar = findViewById(R.id.botonEmparejar);
        listaDispositivos = findViewById(R.id.lista);
        botonEscuchar = findViewById(R.id.btnEscuchar);
        status = findViewById(R.id.status);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        intentoHabilitar = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        mDataBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Pregunta = (TextView) findViewById(R.id.Text_Pregunta);
        grupo = (RadioGroup) findViewById(R.id.grupo);
        NPregunta = (TextView) findViewById(R.id.Np);
        time = (TextView) findViewById(R.id.time);
        rule = findViewById(R.id.ruler);
        textPuntaje = (TextView) findViewById(R.id.textPuntaje);
        puntaje = (TextView) findViewById(R.id.puntaje);
        puntaje.setText(String.valueOf(totalScore));
        textPuntaje.setVisibility(View.INVISIBLE);
        puntaje.setVisibility(View.INVISIBLE);
        Pregunta.setVisibility(View.INVISIBLE);
        grupo.setVisibility(View.INVISIBLE);
        NPregunta.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        rule.setVisibility(View.INVISIBLE);
        codigoHabilitado = 1;
        if (!bluetoothAdapter.isEnabled()) {
            // startActivityForResult(intentoHabilitar,BLUETOOTH_HABILITADO);
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30);
            startActivity(intent);
        }
        botonEscuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", "Entro al evento escuchar");
                ServerClass server = new ServerClass();
                server.start();
            }
        });
        botonEmparejar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emparejar();
            }
        });
        listaDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClientClass cliente = new ClientClass(btArray[position]);
                cliente.start();
                status.setText("Conectando");
                esperarYCerrar(MILISEGUNDOS_ESPERA);
            }
        });


        mDataBase.collection("Preguntas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int i = 0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.e("", "posiciones: " + i);
                        preguntas[i] = document.getData().get("pregunta").toString()
                                + ";" + "*" + document.getData().get("respuestaC").toString()
                                + ";" + document.getData().get("respuestaI1").toString()
                                + ";" + document.getData().get("respuestaI2").toString()
                                + ";" + document.getData().get("respuestaI3").toString();
                        i++;
                    }
                }
            }
        });
        Log.e("","Email: "+mAuth.getCurrentUser().getEmail());
        mDataBase.collection("Users")
                .whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    Log.e("","Consulta: "+document.getData().get("user").toString());
                                    user = document.getData().get("user").toString();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Log.e("", "Error getting documents: ", task.getException());
                        }
                    }
                });
        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(checkedId);
                int index = group.indexOfChild(radioButton);
                respuesta = index;
                if(respuesta == respuesta_correcta){
                    timeQuestion = Integer.parseInt(time.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Log.e("", "Activa bluetooth: " + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == codigoHabilitado) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(Conexion.this, "Bluetooth habilitado", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(Conexion.this, "Bluetooth cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }



  /*  private void apagarBluetooth(){
        botonApagado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                }
            }
        });
    }*/

    public void emparejar() {
        Log.e("", "Entro metodo emparejamiento");
        Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
        String[] lista = new String[bt.size()];
        btArray = new BluetoothDevice[bt.size()];
        int index = 0;
        if (bt.size() > 0) {
            for (BluetoothDevice device : bt) {
                btArray[index] = device;
                lista[index] = device.getName();
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Conexion.this, android.R.layout.simple_list_item_1, lista);
            listaDispositivos.setAdapter(arrayAdapter);
        }

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ESTADO_ESCUCHANDO:
                    status.setText("Escuchando");
                    break;
                case ESTADO_CONECTANDO:
                    status.setText("Conectando");
                    break;
                case ESTADO_CONECTADO:
                    status.setText("Conectado");
                    break;
                case ESTADO_CONEXION_FALLIDA:
                    status.setText("Conexión fallida");
                    break;
                case ESTADO_MENSAJE_RECIBIDO:
                    byte[] readBuff = (byte[]) msg.obj;
                    String mensajeTemporal = new String(readBuff, 0, msg.arg1);
                    Log.e("","Mensaje recibido:"+ mensajeTemporal);
                    if (mensajeTemporal.equals("OK")) {
                        Log.e("", "Empieza a cambiar la vista");
                        botonEmparejar.setVisibility(View.INVISIBLE);
                        botonEscuchar.setVisibility(View.INVISIBLE);
                        status.setVisibility(View.INVISIBLE);
                        listaDispositivos.setVisibility(View.INVISIBLE);
                        if (banPre) {
                            cambiaPregunta();
                        }
                        Pregunta.setVisibility(View.VISIBLE);
                        grupo.setVisibility(View.VISIBLE);
                        NPregunta.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        rule.setVisibility(View.VISIBLE);
                        puntaje.setVisibility(View.VISIBLE);
                        textPuntaje.setVisibility(View.VISIBLE);
                    }else if(mensajeTemporal.contains("{")){
                        userConec = mensajeTemporal.replace("{","");
                        Log.e("","Usuario rival: "+userConec);
                    }else if(mensajeTemporal.contains("}")){
                        totalScoreConec = Integer.parseInt(mensajeTemporal.replace("}",""));
                        Log.e("","Puntaje rival: "+totalScoreConec);
                    }
                    break;
            }
            return true;
        }
    });

    private class ServerClass extends Thread {
        BluetoothServerSocket serverSocket;

        public ServerClass() {
            try {
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME, M_UIID);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        public void run() {
            BluetoothSocket socket = null;
            while (socket == null) {
                try {
                    Message mensaje = Message.obtain();
                    mensaje.what = ESTADO_CONECTADO;
                    handler.sendMessage(mensaje);
                    socket = serverSocket.accept();
                } catch (IOException exception) {
                    exception.printStackTrace();
                    Message mensaje = Message.obtain();
                    mensaje.what = ESTADO_CONEXION_FALLIDA;
                    handler.sendMessage(mensaje);
                }
                if (socket != null) {
                    Message mensaje = Message.obtain();
                    mensaje.what = ESTADO_CONECTADO;
                    handler.sendMessage(mensaje);
                    enviarRecibir = new EnviarRecibir(socket);
                    enviarRecibir.start();
                }
            }
        }
    }

    private class ClientClass extends Thread {
        private BluetoothDevice dispositivo;
        private BluetoothSocket socket;

        public ClientClass(BluetoothDevice dispositivoDefault) {
            dispositivo = dispositivoDefault;
            try {
                socket = dispositivo.createRfcommSocketToServiceRecord(M_UIID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                socket.connect();
                Message mensaje = Message.obtain();
                mensaje.what = ESTADO_CONECTADO;
                handler.sendMessage(mensaje);
                enviarRecibir = new EnviarRecibir(socket);
                enviarRecibir.start();

            } catch (IOException e) {
                e.printStackTrace();
                Message mensaje = Message.obtain();
                mensaje.what = ESTADO_CONEXION_FALLIDA;
                handler.sendMessage(mensaje);
            }

        }
    }

    private class EnviarRecibir extends Thread {
        private final BluetoothSocket bluetoothSocket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        public EnviarRecibir(BluetoothSocket socket) {
            bluetoothSocket = socket;
            InputStream temporalIn = null;
            OutputStream temporalOut = null;
            try {
                temporalIn = bluetoothSocket.getInputStream();
                temporalOut = bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = temporalIn;
            outputStream = temporalOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                try {
                    bytes = inputStream.read(buffer);
                    handler.obtainMessage(ESTADO_MENSAJE_RECIBIDO, bytes, -1, buffer).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startTimer() {
        countTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMillis = millisUntilFinished;
                int seconds = (int) (timeInMillis / 1000) % 60;
                time.setText(String.valueOf(seconds));
            }
            @Override
            public void onFinish() {
                cambiaPregunta();
            }
        }.start();
    }

    private void mostrarPregunta() {
        String pregunta = preguntas[pregunta_actual];
        String[] partes = pregunta.split(";");
        grupo.clearCheck();
        Pregunta.setText(partes[0]);
        NPregunta.setText("Pregunta: " + Total_Preguntas);
        for (int i = 0; i < ids_Respuestas.length; i++) {
            RadioButton rb = (RadioButton) findViewById(ids_Respuestas[i]);
            String respuesta = partes[i + 1];
            if (respuesta.charAt(0) == '*') {
                respuesta_correcta = i;
                respuesta = respuesta.substring(1);
            }
            rb.setText(respuesta);
        }
        timeInMillis = 10000;
        startTimer();
      /*  if (Total_Preguntas == 6) {
            //Finaliza el juego
            Toast.makeText(Conexion.this, "Finalizo Juego", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Conexion.this);
                builder.setMessage("Deseas iniciar la partida");
                builder.setTitle("Multijugador");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mensaje = "OK";
                        enviarRecibir.write(mensaje.getBytes());
                        botonEmparejar.setVisibility(View.INVISIBLE);
                        botonEscuchar.setVisibility(View.INVISIBLE);
                        status.setVisibility(View.INVISIBLE);
                        listaDispositivos.setVisibility(View.INVISIBLE);
                        if (banPre) {
                            cambiaPregunta();
                        }
                        Pregunta.setVisibility(View.VISIBLE);
                        grupo.setVisibility(View.VISIBLE);
                        NPregunta.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        rule.setVisibility(View.VISIBLE);
                        puntaje.setVisibility(View.VISIBLE);
                        textPuntaje.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }, milisegundos);
    }

    private int Repetidas(int numero) {
        for (int i = 0; i < Repe.length; i++) {
            if (Repe[i] == numero) {
                return 0;
            }
        }
        return 1;
    }

    public void cambiaPregunta() {
        Random generadorAleatorios = new Random();
        int numeroAleatorio;
        Log.e("","Respuesta selecionada2: "+respuesta);
        Log.e("","Respuesta correcta2: "+respuesta_correcta);
        if (banPre) {
            numeroAleatorio = 0 + generadorAleatorios.nextInt(20);
            pregunta_actual = numeroAleatorio;
            Total_Preguntas++;
            Repe[0] = numeroAleatorio;
            banPre = false;
            mostrarPregunta();
        } else {
            if (respuesta == respuesta_correcta) {
                scoreQuestion = timeQuestion * 100;
                totalScore = totalScore + scoreQuestion;
                Log.e("","Puntaje: "+totalScore);
                puntaje.setText(String.valueOf(totalScore));
                scoreQuestion = 0;
                timeQuestion = 0;
            }
            if(Total_Preguntas == 3){
                String mensaje = "{"+user;
                enviarRecibir.write(mensaje.getBytes());
            }
            if(Total_Preguntas == 6){
                String mensaje = "}"+totalScore;
                enviarRecibir.write(mensaje.getBytes());
            }
                if (Total_Preguntas < 6) {
                    for(int i=0; i<=19; i++){
                        numeroAleatorio = 0 + generadorAleatorios.nextInt(20);
                        if(Repetidas(numeroAleatorio) == 1){
                            Total_Preguntas++;
                            pregunta_actual = numeroAleatorio;
                            Repe[Total_Preguntas] = numeroAleatorio;
                            mostrarPregunta();
                            break;
                        }
                    }
                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Conexion.this);
                            builder.setMessage("Deseas iniciar otra partida");
                            builder.setTitle(user + " tu puntaje: " + totalScore + " el de tu oponente " + userConec + " " + totalScoreConec);
                            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent homeIntent = new Intent(Conexion.this, Conexion.class);
                                    startActivity(homeIntent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    finish();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                }, 1000);
                }
        }
    }
}