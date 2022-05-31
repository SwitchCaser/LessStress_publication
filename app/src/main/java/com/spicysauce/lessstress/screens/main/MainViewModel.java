package com.spicysauce.lessstress.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.spicysauce.lessstress.model.Note;
import com.spicysauce.lessstress.App;

import java.util.List;

public class MainViewModel extends ViewModel {

    public LiveData<List<Note>> getNoteLiveData() {
        return App.getInstance().getNoteDao().getAllLiveData();
    }

    public LiveData<List<Note>> getNoteWithTagLiveData(int tag) {
        return App.getInstance().getNoteDao().getAllWithTag(tag);
    }
}
