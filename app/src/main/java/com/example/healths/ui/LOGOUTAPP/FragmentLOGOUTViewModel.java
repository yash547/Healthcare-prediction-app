package com.example.healths.ui.LOGOUTAPP;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class FragmentLOGOUTViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FragmentLOGOUTViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("LOGOUT");
      //  FirebaseAuth.getInstance().signOut();

    }



}