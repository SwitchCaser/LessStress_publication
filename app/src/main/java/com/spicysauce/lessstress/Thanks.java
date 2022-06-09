package com.spicysauce.lessstress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.spicysauce.lessstress.R;

public class Thanks extends AppCompatActivity {
    ScrollView scrollView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks2);

        scrollView2 = findViewById(R.id.scrollView2);
        AppCompatButton yes= findViewById(R.id.buttonyes);
        AppCompatButton no= findViewById(R.id.buttonno);
        TextView textViewQ= findViewById(R.id.textViewQ);
        TextView tip2 = findViewById(R.id.textTip2);

        final int[] isTapped1 = {0};
        TextView tip = findViewById(R.id.textTip);
        int activity =  getIntent().getIntExtra("activity",0);
        if (activity==1){
            tip.setText("1. Чтобы включить фильтр по меткам, зажмите нужную метку\n2. Чтобы добавить заметку, удерживайте ячейку даты");
        }
        if (activity==2){
            tip.setText("1. Долгое нажатие на солнышки поможет вам покормить Рюрика\n2. Нажмите на него, чтобы погладить\n3. Нажмите на выключатеь, чтобы уложить его спать");
        }
        AppCompatButton secret = findViewById(R.id.buttonSecret);
        secret.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (isTapped1[0]==0) {
                    tip.setVisibility(View.INVISIBLE);

                    tip2.setVisibility(View.VISIBLE);
                    isTapped1[0] =1;
                }

                else  {
                    tip2.setVisibility(View.GONE);
                    secret.setVisibility(View.GONE);
                    scrollView2.setVisibility(View.GONE);

                    yes.setVisibility(View.VISIBLE);
                    no.setVisibility(View.VISIBLE);
                    textViewQ.setVisibility(View.VISIBLE);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent back3start = new Intent(Thanks.this, Start3.class);
                            startActivity(back3start);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent back3 = new Intent(Thanks.this, MainScreen.class);
                            startActivity(back3);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    });

                    isTapped1[0] =0;
                }

            }
        });
    }
}