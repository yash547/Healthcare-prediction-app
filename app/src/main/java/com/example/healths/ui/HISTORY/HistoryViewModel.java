package com.example.healths.ui.HISTORY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("History");


    }


    public LiveData<String> getText() {
        return mText;
    }
}