package com.spicysauce.lessstress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spicysauce.lessstress.R;

public class Startscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
        findViewById(R.id.next1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swap1 = new Intent(Startscreen.this, Start2.class);
                startActivity(swap1);

            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}