package com.spicysauce.lessstress.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spicysauce.lessstress.MainScreen;
import com.spicysauce.lessstress.screens.details.Adapter;
import com.spicysauce.lessstress.screens.details.NoteDetailsActivity;
import com.example.myapplication.R;
import com.spicysauce.lessstress.model.Note;
import com.spicysauce.lessstress.model.Tag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    final Adapter adapter = new Adapter();

    private RecyclerView recyclerView;

    MainViewModel mainViewModel;
    LiveData<List<Note>> currentLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton home2 = findViewById(R.id.back_home);
        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent h = new Intent(MainActivity4.this, MainScreen.class);
                startActivity(h);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteDetailsActivity.start(MainActivity4.this, null);
            }
        });

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        applyTag(0);

        final RadioGroup radioGroup = findViewById(R.id.radio_group);
        findViewById(R.id.t1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyTag(Tag.red);
            }
        });
        findViewById(R.id.t2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyTag(Tag.blue);
            }
        });
        findViewById(R.id.t3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyTag(Tag.green);
            }
        });
        findViewById(R.id.t_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyTag(0);
                radioGroup.clearCheck();
            }
        });
    }

    private void applyTag(int tag) {
        if (currentLiveData != null) {
            currentLiveData.removeObservers(this);
        }

        if (tag == 0) {
            currentLiveData = mainViewModel.getNoteLiveData();
        } else {
            currentLiveData = mainViewModel.getNoteWithTagLiveData(tag);
        }

        currentLiveData.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setItems(notes);
            }
        });
    }
}
