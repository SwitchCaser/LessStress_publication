package com.spicysauce.lessstress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ImageButton back_store = findViewById(R.id.back_store);
        back_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back2 = new Intent(Store.this, Tamagochi.class);
                startActivity(back2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}