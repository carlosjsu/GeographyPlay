package com.udistrital.geographyplay;

import android.app.Activity;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class login extends AppCompatActivity {

    Button registrarse;
    EditText email;
    EditText pasword;
    Button login;
    private FirebaseAuth mAuth;
    private static final String AES = "AES";
    private FirebaseFirestore mDataBase;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseFirestore.getInstance();
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
                                    Log.e("", "contraseña encriptada:  " +passEncrip[0] + ", " +document.getData().get("password"));
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
      /*  mAuth.signInWithEmailAndPassword(email.getText().toString(), passEncrip[0])
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
                });*/
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
        mAuth.signInWithEmailAndPassword(email.getText().toString(), passEncrip)
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
