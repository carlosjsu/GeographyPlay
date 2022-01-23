package com.udistrital.geographyplay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class Conexion extends AppCompatActivity {

    private Button botonEmparejar, botonEscuchar, botonEnviar;
    private BluetoothAdapter bluetoothAdapter;
    private Intent intentoHabilitar;
    private  int codigoHabilitado;
    private ListView listaDispositivos;
    private TextView mensajeRecidibo,status;
    private BluetoothDevice[] btArray;
    private EditText mensajeEnviar;
    private EnviarRecibir enviarRecibir;
    static final int ESTADO_ESCUCHANDO = 1;
    static final int ESTADO_CONECTANDO = 2;
    static final int ESTADO_CONECTADO = 3;
    static final int ESTADO_CONEXION_FALLIDA = 4;
    static final int ESTADO_MENSAJE_RECIBIDO = 5;
    private Boolean bandera = true;

    int BLUETOOTH_HABILITADO =1;
    private static final String APP_NAME="GeographyPlayOnline";
    private static final UUID M_UIID = UUID.fromString("efb6c78c-7c45-11ec-90d6-0242ac120003");
    //Generador uuid random UUID.randomUUID().toString()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);
        botonEmparejar= findViewById(R.id.botonEmparejar);
        listaDispositivos = findViewById(R.id.lista);
        botonEscuchar = findViewById(R.id.btnEscuchar);
        mensajeRecidibo = findViewById(R.id.mensaje);
        status = findViewById(R.id.status);
        mensajeEnviar = findViewById(R.id.escritura);
        botonEnviar = findViewById(R.id.btnEnviar);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        intentoHabilitar = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        codigoHabilitado = 1;
        if(!bluetoothAdapter.isEnabled()){
           // startActivityForResult(intentoHabilitar,BLUETOOTH_HABILITADO);
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30);
            startActivity(intent);
        }
        botonEscuchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("","Entro al evento escuchar");
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
            }
        });
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = String.valueOf(mensajeEnviar.getText());
                enviarRecibir.write(mensaje.getBytes());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Log.e("","Activa bluetooth: "+ requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == codigoHabilitado){
            if(resultCode == RESULT_OK){
                Toast.makeText(Conexion.this, "Bluetooth habilitado", Toast.LENGTH_SHORT).show();
            }else if(resultCode == RESULT_CANCELED){
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

    public void emparejar(){
        Log.e("","Entro metodo emparejamiento");
        Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
        String[] lista = new String[bt.size()];
        btArray = new BluetoothDevice[bt.size()];
        int index = 0;
        if(bt.size() > 0){
            Log.e("","Entro if emparejamiento");
            for(BluetoothDevice device : bt){
                Log.e("","Entro for emparejamiento: "+index);
                btArray[index] = device;
                lista [index] = device.getName();
                Log.e("","Entro for emparejamiento nombre: "+lista [index]);
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Conexion.this, android.R.layout.simple_list_item_1, lista);
            listaDispositivos.setAdapter(arrayAdapter);
        }

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
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
                    String mensajeTemporal = new String(readBuff,0,msg.arg1);
                    mensajeRecidibo.setText(mensajeTemporal);
                    break;
            }
            return true;
        }
    });

    private class ServerClass extends Thread
    {
        BluetoothServerSocket serverSocket;
        public ServerClass(){
            try {
                serverSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME, M_UIID);
            } catch (IOException exception){
                exception.printStackTrace();
            }
        }
        public void run(){
        BluetoothSocket socket = null;
        while(socket == null){
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
            if(socket != null){
                Message mensaje = Message.obtain();
                mensaje.what = ESTADO_CONECTADO;
                handler.sendMessage(mensaje);
                enviarRecibir = new EnviarRecibir(socket);
                enviarRecibir.start();
            }
        }
        }
    }

    private class ClientClass extends Thread
    {
        private BluetoothDevice dispositivo;
        private BluetoothSocket socket;

        public ClientClass(BluetoothDevice dispositivoDefault){
            dispositivo = dispositivoDefault;
            try {
                socket = dispositivo.createRfcommSocketToServiceRecord(M_UIID);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        public void run(){
            try {
                socket.connect();
                Message mensaje = Message.obtain();
                mensaje.what = ESTADO_CONECTADO;
                handler.sendMessage(mensaje);
                enviarRecibir = new EnviarRecibir(socket);
                enviarRecibir.start();
            }catch (IOException e){
                e.printStackTrace();
                Message mensaje = Message.obtain();
                mensaje.what = ESTADO_CONEXION_FALLIDA;
                handler.sendMessage(mensaje);
            }

        }
    }

    private class EnviarRecibir extends Thread
    {
        private final BluetoothSocket bluetoothSocket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        public EnviarRecibir(BluetoothSocket socket){
            bluetoothSocket= socket;
            InputStream temporalIn = null;
            OutputStream temporalOut=null;
            try {
                temporalIn = bluetoothSocket.getInputStream();
                temporalOut = bluetoothSocket.getOutputStream();
            }catch (IOException e){
                e.printStackTrace();
            }
            inputStream = temporalIn;
            outputStream =temporalOut;
        }

        public void run(){
            byte [] buffer = new byte[1024];
            int bytes;
            while(true){
                try {
                    bytes = inputStream.read(buffer);
                    handler.obtainMessage(ESTADO_MENSAJE_RECIBIDO, bytes, -1, buffer).sendToTarget();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] bytes){
            try{
                outputStream.write(bytes);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}