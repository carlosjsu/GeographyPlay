package com.udistrital.geographyplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by AsusPC on 28/03/2018.
 */

public class pais1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pais1_layout,container,false);
    }
}
