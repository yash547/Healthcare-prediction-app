package com.example.healths.ui.Medicine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicineViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<String> mText;

    public MedicineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Medicine");
    }

    public LiveData<String> getText() {
        return mText;
    }
}