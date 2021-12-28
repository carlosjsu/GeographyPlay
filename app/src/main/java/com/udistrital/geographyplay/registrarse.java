package com.udistrital.geographyplay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class registrarse extends AppCompatActivity {

    EditText email;
    EditText pasword;
    EditText user;
    Button registrarse;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private static final String AES = "AES";

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference("");
        email = findViewById(R.id.correoRegistro);
        pasword = findViewById(R.id.contraseniaRegistro);
        user = findViewById(R.id.usuarioRegistro);
        registrarse = findViewById(R.id.btnRegistro);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(!validarEmail(email.getText().toString())) {
                    Toast.makeText(registrarse.this, "Correo no valido", Toast.LENGTH_SHORT).show();
                }if(pasword.getText().toString().length()<6){
                    Toast.makeText(registrarse.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        registro();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void registro() throws Exception {
        SecretKeySpec key = generarClave();
        String passEncrip= encriptar(key,pasword.getText().toString());
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),passEncrip)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Map<String, Object> map = new HashMap<>();
                            map.put("user", user.getText().toString());
                            map.put("email", email.getText().toString());
                            map.put("password", passEncrip);
                            map.put("app",key.toString());

                            String id = mAuth.getCurrentUser().getUid();
                            mDataBase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task2) {
                                    if(task2.isSuccessful()){
                                        try {
                                            Toast.makeText(registrarse.this, "Autenticación satisfactoria: "+desEncriptar(key,passEncrip),
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        registroExitoso(task.getResult().getUser().getEmail());
                                    }else{
                                        Toast.makeText(registrarse.this, "Fallo autenticación dataBase",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(registrarse.this, "Fallo autenticación",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void registroExitoso(String email){
        Intent registrarse = new Intent(registrarse.this, login.class);
        registrarse.putExtra("email", email);
        //registrarse.putExtra("pasword", pasword.toString());
        startActivity(registrarse);
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private SecretKeySpec generarClave() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesSecretKey = secretKey.getEncoded();
        return new SecretKeySpec(bytesSecretKey,AES);
    }

    private String encriptar(SecretKeySpec secretKeySpec, String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        byte[] mensajeEn = cipher.doFinal(mensaje.getBytes(StandardCharsets.UTF_8));
        return new String(mensajeEn);
    }

    private String desEncriptar(SecretKeySpec secretKeySpec, String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] mensajeDes = cipher.doFinal(mensaje.getBytes(StandardCharsets.UTF_8));
        return new String(mensajeDes);
    }
}
