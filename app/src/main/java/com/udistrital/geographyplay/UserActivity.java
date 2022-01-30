package com.udistrital.geographyplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDataBase;
    private String user = "";
    private String correo = "";
    Button salir;
    TextView usuario;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        salir = (Button) findViewById(R.id.btnExit);
        usuario = (TextView) findViewById(R.id.txtUserOb);
        email = (TextView) findViewById(R.id.txtEmailUserOb);

        mDataBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        correo = mAuth.getCurrentUser().getEmail();

        mDataBase.collection("Users")
                .whereEqualTo("email", correo)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    user = document.getData().get("user").toString();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            usuario.setText(user);
                            email.setText(correo);
                        } else {
                            Log.e("", "Error getting documents: ", task.getException());
                        }
                    }
                });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", "Se cerr sesion correctamente");
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(UserActivity.this, login.class);
                startActivity(logout);
            }
        });
    }
}