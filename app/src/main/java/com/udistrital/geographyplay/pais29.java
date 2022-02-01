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


public class pais29 extends Fragment {
    @Nullable

    View view;
    MediaPlayer mp;
    ImageButton ibCristo;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pais29_layout,container,false);
        ibCristo = (ImageButton) view.findViewById(R.id.ibtnCristo);
        if(Locale.getDefault().toString().contains("en_")){
            mp = MediaPlayer.create(getContext(), R.raw.sancristobalen);
        }else{
            mp = MediaPlayer.create(getContext(), R.raw.sancristobal);
        }

        ibCristo.setOnClickListener(new View.OnClickListener() {
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
