package com.udistrital.geographyplay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class login extends AppCompatActivity {

    Button registrarse;
    EditText email;
    EditText pasword;
    Button login;
    private FirebaseAuth mAuth;
    private static final String AES = "AES";
    private FirebaseFirestore mDataBase;
    private Button recuperar;
    private Boolean bandera = false;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseFirestore.getInstance();
        email = findViewById(R.id.email);
        pasword = findViewById(R.id.pasword);
        registrarse=(Button)findViewById(R.id.registro);
        recuperar=(Button)findViewById(R.id.recuperar);
        login=(Button)findViewById(R.id.login);
        mDialog = new ProgressDialog(this);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(login.this, registrarse.class);
                startActivity(registrarse);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDialog.setMessage("Espere un momento.....");
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
                if(!email.getText().toString().isEmpty() && !pasword.getText().toString().isEmpty()){
                    login();
                }else{
                    mDialog.dismiss();
                    Toast.makeText(login.this, "Por favor diligencie los campos email y contrase??a",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recuperar = new Intent(login.this, RecuperarClave.class);
                startActivity(recuperar);
            }
        });
    }
    private void login(){
        final String[] passEncrip = new String[1];
        mDataBase.collection("Users")
                .whereEqualTo("email", email.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    passEncrip[0] = encriptar(document.getData().get("app").toString(), pasword.getText().toString());
                                    Log.e("", "contrase??a encriptada:  " +passEncrip[0] + ", " +document.getData().get("password"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            validateLogin(passEncrip[0]);

                        } else {
                            Log.e("", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    private String encriptar(String stringKey, String mensaje) throws Exception {
        byte[] encodedKey     = Base64.decode(stringKey, Base64.DEFAULT);
        SecretKeySpec originalKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,originalKey);
        byte[] mensajeEn = cipher.doFinal(mensaje.getBytes());
        String mensajeEncrip = Base64.encodeToString(mensajeEn, Base64.DEFAULT);
        return mensajeEncrip;
    }

    private void validateLogin(String passEncrip){
        if(passEncrip != null) {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), passEncrip)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent menu = new Intent(login.this, MainActivity.class);
                                startActivity(menu);
                            }else{
                                if(!bandera) {
                                    bandera = true;
                                    validateLogin(pasword.getText().toString());
                                }else{
                                    mDialog.dismiss();
                                    Toast.makeText(login.this, "Correo y/o contrase??a incorrecta",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });
        }else{
            mDialog.dismiss();
            Toast.makeText(login.this, "Correo y/o contrase??a incorrecta",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
