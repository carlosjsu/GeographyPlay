package com.udistrital.geographyplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * Created by AsusPC on 28/03/2018.
 */

public class pais1 extends Fragment {
    @Nullable

    View view;
    MediaPlayer mp;
    ImageButton ibAntigua;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pais1_layout,container,false);
        ibAntigua = (ImageButton) view.findViewById(R.id.ibtnAntigua);
        if(Locale.getDefault().toString().equals("es_CO")){
            mp = MediaPlayer.create(getContext(), R.raw.antiguaybarbuda);
        }else{
            mp = MediaPlayer.create(getContext(), R.raw.antiguaandbarbudaen);
        }

        ibAntigua.setOnClickListener(new View.OnClickListener() {
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
