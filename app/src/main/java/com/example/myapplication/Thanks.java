package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Thanks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks2);
        TextView tip = findViewById(R.id.textTip);
        int activity =  getIntent().getIntExtra("activity",0);
        if (activity==1){
            tip.setText("1.Чтобы включить фильтр по меткам, зажмите нужную метку\n2.Чтобы добавить заметку, удерживайте ячейку даты");

        }
    }
}