package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {
    ImageButton karambola,game,calendar, todo,lamp1;
    AnimationDrawable animation;
    ImageButton sos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        sos = findViewById(R.id.next34_button);
        todo =findViewById(R.id.tools_icon);
        game = findViewById(R.id.game_icon);
        game = findViewById(R.id.tools_icon);
        calendar = findViewById(R.id.calendar_icon);
        lamp1 = findViewById(R.id.lamp_main);
        lamp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lampM = new Intent(MainScreen.this, Thanks.class);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGame = new Intent(MainScreen.this, Tamagochi.class);
                startActivity(intentGame);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalendar = new Intent(MainScreen.this, Calendar.class);
                startActivity(intentCalendar);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCalendar = new Intent(MainScreen.this, App.class);
                startActivity(intentCalendar);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        final SharedPreferences loginData = getSharedPreferences("loginData", MODE_PRIVATE);
        boolean firstStart = loginData.getBoolean("firstStart", true);
        if(firstStart) {
            loginData.edit().putBoolean("firstStart", false).commit();

            Intent intent = new Intent(this,Startscreen.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }


        karambola = (ImageButton)findViewById(R.id.karambola);
        karambola.setBackgroundResource(R.drawable.happy);
        animation = (AnimationDrawable) karambola.getBackground();
        MediaPlayer mp1 = MediaPlayer.create(this, R.raw.g1);
        MediaPlayer mp2 = MediaPlayer.create(this, R.raw.g2);
        MediaPlayer mp3 = MediaPlayer.create(this, R.raw.g3);
        MediaPlayer mp4 = MediaPlayer.create(this, R.raw.g4);
        MediaPlayer mp5 = MediaPlayer.create(this, R.raw.g5);
        animation.setOneShot(true);
        karambola.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                animation.stop();
                animation.start();
                int x = (int)(Math.random()*((5-1)+1))+1;
                switch (x) {
                    case(1):
                        mp1.start();
                        break;
                    case(2):
                        mp2.start();
                        break;
                    case(3):
                        mp3.start();
                        break;
                    case(4):
                        mp4.start();
                        break;
                    case(5):
                        mp5.start();
                        break;
                }

                return false;
            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sosIntent = new Intent(MainScreen.this, MainActivity.class);
                startActivity(sosIntent);
            }
        });

    }
}