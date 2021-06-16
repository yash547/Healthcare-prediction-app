package com.example.healths.ui.gallery;

import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Disease Prediction");


    }


    public LiveData<String> getText() {
        return mText;
    }
}