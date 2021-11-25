package com.udistrital.geographyplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class login extends AppCompatActivity {

    Button registrarse;
    EditText email;
    EditText pasword;
    Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
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
}
