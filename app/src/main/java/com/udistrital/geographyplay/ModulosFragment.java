package com.udistrital.geographyplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.udistrital.geographyplay.databinding.FragmentModulosBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModulosFragment#} factory method to
 * create an instance of this fragment.
 */
public class ModulosFragment extends Fragment {

    View vista;
    CardView sinAR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_modulos, container, false);
        sinAR= vista.findViewById(R.id.cardSinAr);
        sinAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Interactivo.class);
                startActivity(intent);
            }
        });
        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}