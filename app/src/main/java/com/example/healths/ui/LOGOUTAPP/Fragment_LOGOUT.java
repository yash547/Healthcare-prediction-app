package com.example.healths.ui.LOGOUTAPP;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healths.Login;
import com.example.healths.R;
import com.google.firebase.auth.FirebaseAuth;

public class Fragment_LOGOUT extends Fragment {

    private FragmentLOGOUTViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)

   {
//        mViewModel =
//                new ViewModelProvider(this).get(FragmentLOGOUTViewModel.class);
        View root = inflater.inflate(R.layout.fragment__logout, container, false);


        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), Login.class));

        return root;
    }

}