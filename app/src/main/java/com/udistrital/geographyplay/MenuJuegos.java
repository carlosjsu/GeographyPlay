package com.udistrital.geographyplay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.udistrital.geographyplay.databinding.FragmentMenuJuegosBinding;

public class MenuJuegos extends Fragment {

    private FragmentMenuJuegosBinding binding;
    View vista;
    CardView cardPuzzle;
    CardView cardConcen;
    CardView cardSaber;
    CardView cardOnline;
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
        cardPuzzle = vista.findViewById(R.id.cardPuzzle);
        cardConcen = vista.findViewById(R.id.cardConcen);
        cardSaber = vista.findViewById(R.id.cardSaber);
        cardOnline = vista.findViewById(R.id.cardOnline);
        cardPuzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SelectPuzle.class);
                startActivity(intent);
            }
        });
        cardConcen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectConcentrese.class);
                startActivity(intent);
            }
        });
        cardSaber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Conocimientos.class);
                startActivity(intent);
            }
        });
        cardOnline.setOnClickListener(new View.OnClickListener() {
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