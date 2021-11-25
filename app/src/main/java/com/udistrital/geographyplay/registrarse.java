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

import java.util.regex.Pattern;

public class registrarse extends AppCompatActivity {

    EditText email;
    EditText pasword;
    Button registrarse;
    private FirebaseAuth mAuth;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.correoRegistro);
        pasword = findViewById(R.id.contraseniaRegistro);
        registrarse = findViewById(R.id.btnRegistro);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(!validarEmail(email.getText().toString())) {
                    Toast.makeText(registrarse.this, "Correo no valido", Toast.LENGTH_SHORT).show();
                }if(pasword.getText().toString().length()<6){
                    Toast.makeText(registrarse.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                }else{
                    registro();
                }
            }
        });
    }

    private void registro(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),pasword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(registrarse.this, "Autenticación satisfactoria",
                                    Toast.LENGTH_SHORT).show();
                            registroExitoso(task.getResult().getUser().getEmail());
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


}
