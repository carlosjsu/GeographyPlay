package com.udistrital.geographyplay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.udistrital.geographyplay.R;
import com.udistrital.geographyplay.databinding.FragmentMenuJuegosBinding;

public class MenuJuegos extends Fragment {

    private FragmentMenuJuegosBinding binding;
    View vista;
    Button memorama;
    Button puzle;
    Button preguntas;
    Button online;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_menu_juegos, container, false);
        memorama=(Button)  vista.findViewById(R.id.btnConcentrese);
        online =(Button)  vista.findViewById(R.id.btnOnline);
        memorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectConcentrese.class);
                startActivity(intent);
            }
        });
        puzle=(Button)  vista.findViewById(R.id.btnPuzle);
        puzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectPuzle.class);
                startActivity(intent);
            }
        });
        preguntas=(Button)  vista.findViewById(R.id.btnPreguntas);
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Conocimientos.class);
                startActivity(intent);
            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Conexion.class);
                startActivity(intent);
            }
        });
        return vista;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}