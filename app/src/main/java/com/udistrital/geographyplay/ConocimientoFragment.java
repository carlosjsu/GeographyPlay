package com.udistrital.geographyplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udistrital.geographyplay.databinding.FragmentConocimientoBinding;


public class ConocimientoFragment extends Fragment {
    private FragmentConocimientoBinding binding;
    View vista;
    Button cestionario;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_conocimiento, container, false);
        cestionario=(Button) vista.findViewById(R.id.IniciarCuestionario);
        cestionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Saber.class);
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