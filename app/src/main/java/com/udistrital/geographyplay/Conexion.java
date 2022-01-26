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
    private int Repe[] = {15, 15, 85, 98, 54, 69, 25, 50};
    private int respuesta_correcta;
    private int Total_Preguntas = 0;
    private int pregunta_actual;
    private TextView Pregunta;
    private TextView NPregunta;
    private TextView time;
    private RadioGroup grupo;
    private View rule;
    private Boolean banPre = true;
    int BLUETOOTH_HABILITADO = 1;
    private static final String APP_NAME = "GeographyPlayOnline";
    private static final UUID M_UIID = UUID.fromString("efb6c78c-7c45-11ec-90d6-0242ac120003");

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
        Pregunta = (TextView) findViewById(R.id.Text_Pregunta);
        grupo = (RadioGroup) findViewById(R.id.grupo);
        NPregunta = (TextView) findViewById(R.id.Np);
        time = (TextView) findViewById(R.id.time);
        rule = findViewById(R.id.ruler);
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
        Random generadorAleatorios = new Random();
        final int numeroAleatorio = 0 + generadorAleatorios.nextInt(20);
        pregunta_actual = numeroAleatorio;
        Total_Preguntas++;
        Repe[0] = numeroAleatorio;

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
                    //mostrarPregunta();
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
            Log.e("", "Entro if emparejamiento");
            for (BluetoothDevice device : bt) {
                Log.e("", "Entro for emparejamiento: " + index);
                btArray[index] = device;
                lista[index] = device.getName();
                Log.e("", "Entro for emparejamiento nombre: " + lista[index]);
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
                    if (mensajeTemporal.equals("OK")) {
                        Log.e("", "Empieza a cambiar la vista");
                        botonEmparejar.setVisibility(View.INVISIBLE);
                        botonEscuchar.setVisibility(View.INVISIBLE);
                        status.setVisibility(View.INVISIBLE);
                        listaDispositivos.setVisibility(View.INVISIBLE);
                        mostrarPregunta();
                        Pregunta.setVisibility(View.VISIBLE);
                        grupo.setVisibility(View.VISIBLE);
                        NPregunta.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        rule.setVisibility(View.VISIBLE);
                        startTimer();
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
        if (Total_Preguntas == 6) {
            //Finaliza el juego
            Toast.makeText(Conexion.this, "Finalizo Juego", Toast.LENGTH_SHORT).show();
        }
        timeInMillis = 10000;
        startTimer();
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
                        mostrarPregunta();
                        Pregunta.setVisibility(View.VISIBLE);
                        grupo.setVisibility(View.VISIBLE);
                        NPregunta.setVisibility(View.VISIBLE);
                        time.setVisibility(View.VISIBLE);
                        rule.setVisibility(View.VISIBLE);
                        startTimer();
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
        Log.e("","Entro a cambiar pregunta: ");
        if (banPre) {
            banPre = false;
            mostrarPregunta();
        } else {
            Log.e("","Llego a cambiar la pregunta: "+Total_Preguntas);
            int id = grupo.getCheckedRadioButtonId();
            int respuesta = -1;
            for (int i = 0; i < ids_Respuestas.length; i++) {
                if (ids_Respuestas[i] == id) {
                    respuesta = i;
                }
            }
                Random generadorAleatorios = new Random();
                if (Total_Preguntas < 6) {
                    int numeroAleatorio = 0 + generadorAleatorios.nextInt(20);
                    if (Repetidas(numeroAleatorio) == 1) {
                        Total_Preguntas++;
                        pregunta_actual = numeroAleatorio;
                        Repe[Total_Preguntas] = numeroAleatorio;
                        mostrarPregunta();
                    } else {
                        int numeroAleatorio1 = 0 + generadorAleatorios.nextInt(20);
                        if (Repetidas(numeroAleatorio1) == 1) {
                            Total_Preguntas++;
                            Repe[Total_Preguntas] = numeroAleatorio1;
                            pregunta_actual = numeroAleatorio1;
                            mostrarPregunta();
                        } else {
                            int numeroAleatorio2 = 0 + generadorAleatorios.nextInt(20);
                            if (Repetidas(numeroAleatorio2) == 1) {
                                Total_Preguntas++;
                                Repe[Total_Preguntas] = numeroAleatorio2;
                                pregunta_actual = numeroAleatorio2;
                                mostrarPregunta();
                            }
                        }
                    }
                } else {
                    Toast.makeText(Conexion.this, "Felicidades Superaste el Modulo de Saber!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}