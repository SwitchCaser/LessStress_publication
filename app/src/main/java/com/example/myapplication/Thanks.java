package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;

public class Thanks extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks2);
        final boolean[] isTapped1 = {false};
        TextView tip = findViewById(R.id.textTip);
        int activity =  getIntent().getIntExtra("activity",0);
        if (activity==1){
            tip.setText("1. Чтобы включить фильтр по меткам, зажмите нужную метку\n2. Чтобы добавить заметку, удерживайте ячейку даты");
        }
        if (activity==2){
            tip.setText("1. Долгое нажатие на солнышки поможет вам покормить Рюрика 2. Нажмите на него, чтобы погладить 3. Нажмите на выключатеь, чтобы уложить его спать");
        }
        AppCompatButton secret = findViewById(R.id.buttonSecret);
        secret.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (isTapped1[0] == false) {
                    tip.setVisibility(View.INVISIBLE);
                    TextView tip2 = findViewById(R.id.textTip2);
                    tip2.setVisibility(View.VISIBLE);
                    isTapped1[0] =true;
                }

                else {
                    Intent back3 = new Intent(Thanks.this, MainScreen.class);
                    startActivity(back3);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }

            }
        });
    }
}