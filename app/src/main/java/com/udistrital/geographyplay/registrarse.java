package com.udistrital.geographyplay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

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
    private FirebaseFirestore mDataBase;
    private static final String AES = "AES";
    private ProgressDialog mDialog;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseFirestore.getInstance();
        email = findViewById(R.id.correoRegistro);
        pasword = findViewById(R.id.contraseniaRegistro);
        user = findViewById(R.id.usuarioRegistro);
        registrarse = findViewById(R.id.btnRegistro);
        mDialog = new ProgressDialog(this);
        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(!validarEmail(email.getText().toString())) {
                    Toast.makeText(registrarse.this, "Correo no valido", Toast.LENGTH_SHORT).show();
                }if(!validatePassword()){
                    try {
                        mDialog.setMessage("Espere un momento.....");
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.show();
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
                            String id = mAuth.getCurrentUser().getUid();
                            String stringKey = Base64.encodeToString(key.getEncoded(), Base64.DEFAULT);
                            Map<String, Object> map = new HashMap<>();
                            map.put("user", user.getText().toString());
                            map.put("email", email.getText().toString());
                            map.put("password", passEncrip);
                            map.put("app",stringKey);
                            mDialog.dismiss();
                            mDataBase.collection("Users").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    try {
                                        Toast.makeText(registrarse.this, "Registro satisfactorio: ",
                                                Toast.LENGTH_SHORT).show();
                                        Intent menu = new Intent(registrarse.this, MainActivity.class);
                                        startActivity(menu);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Log.e("Error: ","Error base de datos: "+e);
                                    Toast.makeText(registrarse.this, "Fallo autenticación dataBase",
                                            Toast.LENGTH_SHORT).show();
                                    registroExitoso(task.getResult().getUser().getEmail());
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
        byte[] mensajeEn = cipher.doFinal(mensaje.getBytes());
        String mensajeEncrip = Base64.encodeToString(mensajeEn, Base64.DEFAULT);
        return mensajeEncrip;
    }

    /*private String desEncriptar(SecretKeySpec secretKeySpec, String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] mensajeDeco = Base64.decode(mensaje, Base64.DEFAULT);
        byte[] mensajeByte = cipher.doFinal(mensajeDeco);
        String mensajeDes = new String(mensajeByte);
        return mensajeDes;
    }*/

    private Boolean validatePassword(){

            boolean validacion = false;
            if (!pasword.getText().toString().matches(".*[!@#$%^&*+=?-].*")){
                Toast.makeText(registrarse.this, "La contraseña debe tener al menos 1 caracter especial", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

            if (!pasword.getText().toString().matches(".*\\d.*")){
                Toast.makeText(registrarse.this, "La contraseña debe tener al menos 1 caracter númerico", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

            if (!pasword.getText().toString().matches(".*[a-z].*")){
                Toast.makeText(registrarse.this, "La contraseña debe tener al menos 1 caracter en minuscula", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

            if (!pasword.getText().toString().matches(".*[A-Z].*")){
                Toast.makeText(registrarse.this, "La contraseña debe tener al menos 1 caracter en mayuscula", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

            if (!pasword.getText().toString().matches(".{8,15}")){
                Toast.makeText(registrarse.this, "La contraseña debe tener minimo 8 caracteres y maximo 15", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

            if (pasword.getText().toString().matches(".*\\s.*")){
                Toast.makeText(registrarse.this, "La contraseña no debe contener espacios", Toast.LENGTH_SHORT).show();
                validacion = true;
            }

        return validacion;
    }
}
