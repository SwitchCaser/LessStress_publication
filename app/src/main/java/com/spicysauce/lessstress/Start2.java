package com.spicysauce.lessstress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class Start2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
        findViewById(R.id.next2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swap2 = new Intent(Start2.this, Start3.class);
                startActivity(swap2);
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}