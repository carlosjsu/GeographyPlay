package com.udistrital.geographyplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class RecuperarClave extends AppCompatActivity {

    private EditText email;
    private Button botonRecuperar;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_clave);
        email = findViewById(R.id.email);
        botonRecuperar = findViewById(R.id.botonRecuperar);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        botonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarEmail(email.getText().toString())){
                    mDialog.setMessage("Espere un momento.....");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    recuperarClave();
                }else{
                    Toast.makeText(RecuperarClave.this, "Correo no valido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void recuperarClave(){
    mAuth.setLanguageCode("es");
    mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull @NotNull Task<Void> task) {
            if(task.isSuccessful()){
                Toast.makeText(RecuperarClave.this, "Se ha enviado un correo para restablecer tu contraseña", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RecuperarClave.this, "No se pudo enviar el correo de recuperación", Toast.LENGTH_SHORT).show();
            }
            mDialog.dismiss();
        }
    });
    }
}