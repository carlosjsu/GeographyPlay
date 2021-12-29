package com.udistrital.geographyplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class login extends AppCompatActivity {

    Button registrarse;
    EditText email;
    EditText pasword;
    Button login;
    private FirebaseAuth mAuth;
    private static final String AES = "AES";
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        email = findViewById(R.id.email);
        pasword = findViewById(R.id.pasword);
        registrarse=(Button)findViewById(R.id.registro);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(login.this, registrarse.class);
                startActivity(registrarse);
            }
        });
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !pasword.getText().toString().isEmpty()){
                    login();
                }else{
                    Toast.makeText(login.this, "Por favor diligencie los campos email y contraseña",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void login(){
        Query consulta = mDataBase.child("Users").orderByChild("email").equalTo(email.getText().toString());
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String user = dataSnapshot.child("app").getValue().toString();
                    Toast.makeText(login.this, "Encontro campo"+user,
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        mAuth.signInWithEmailAndPassword(email.getText().toString(),pasword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent menu = new Intent(login.this, MainActivity.class);
                            startActivity(menu);
                        }else{
                            Toast.makeText(login.this, "Correo y/o contraseña incorrecta",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private String encriptar(SecretKeySpec secretKeySpec, String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        byte[] mensajeEn = cipher.doFinal(mensaje.getBytes());
        String mensajeEncrip = Base64.encodeToString(mensajeEn, Base64.DEFAULT);
        return mensajeEncrip;
    }
}
