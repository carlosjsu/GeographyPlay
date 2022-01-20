package com.udistrital.geographyplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.udistrital.geographyplay.ui.main.SectionsPagerAdapter;
import com.udistrital.geographyplay.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ModulosFragment.OnFragmentInteractionListener, MenuJuegos.OnFragmentInteractionListener  {

    private ActivityMainBinding binding;
    private ImageView cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("","Cerro sesion");
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(MainActivity.this, login.class);
                startActivity(login);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void onBackPressed() {

    }
}