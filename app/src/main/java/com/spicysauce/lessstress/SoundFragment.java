package com.spicysauce.lessstress;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.example.myapplication.R;


public class SoundFragment extends Fragment {
    ImageButton play1,play2,pause1,pause2;
    SeekBar sound1, sound2;
    MediaPlayer mp1, mp2;
    Handler h1 = new Handler();
    Handler h2 = new Handler();
    Runnable run1, run2;


    public SoundFragment() {

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
         play1 = (ImageButton) view.findViewById(R.id.play1);
         play2 = (ImageButton) view.findViewById(R.id.play2);
         pause1 = (ImageButton) view.findViewById(R.id.pause1);
         pause2 = (ImageButton) view.findViewById(R.id.pause2);
         sound1 = (SeekBar) view.findViewById(R.id.sound1);
         sound2 = (SeekBar) view.findViewById(R.id.sound2);
         mp1 = MediaPlayer.create(view.getContext(), R.raw.med);
         mp2 = MediaPlayer.create(view.getContext(), R.raw.help);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.start();
                sound1.setMax(mp1.getDuration());
                h1.postDelayed(run1,0);
            }
        });
        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.pause();
                h1.removeCallbacks(run1);
            }
        });
        sound1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mp1.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp1.seekTo(0);
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.start();
                sound2.setMax(mp2.getDuration());
                h1.postDelayed(run2,0);
            }
        });
        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp2.pause();
                h2.removeCallbacks(run2);
            }
        });
        sound2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mp2.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp2.seekTo(0);
            }
        });

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        run1 = new Runnable() {
            @Override
            public void run() {
                sound1.setProgress(mp1.getCurrentPosition());
                h1.postDelayed(this,500);
            }
        };

        run2 = new Runnable() {
            @Override
            public void run() {
                sound2.setProgress(mp2.getCurrentPosition());
                h2.postDelayed(this,500);
            }
        };



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sound, container, false);


    }
}