package com.udistrital.geographyplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Locale;


public class pais6 extends Fragment {
    @Nullable

    View view;
    MediaPlayer mp;
    ImageButton ibBolivia;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pais6_layout,container,false);
        ibBolivia = (ImageButton) view.findViewById(R.id.ibtnBolivia);
        if(Locale.getDefault().toString().equals("es_CO")){
            mp = MediaPlayer.create(getContext(), R.raw.bolivia);
        }else{
            mp = MediaPlayer.create(getContext(), R.raw.boliviaen);
        }

        ibBolivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mp.isPlaying()){
                    mp.start();
                }
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if (!isVisibleToUser) {
                if(mp.isPlaying()){
                    Log.d("MyFragment", "Not visible anymore. Stopping audio.");
                    mp.pause();
                    mp.reset();
                }
            }
        }
    }
}
