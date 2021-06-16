package com.example.healths.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healths.R;
import com.example.healths.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
//
//
    DatabaseReference reference;
    FirebaseUser user;
    String userID;
    TextView NAME,AGE,EMAILID,MOBILENO,PASSWORD;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });


//
//
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();

        NAME =  root.findViewById(R.id.textView3);
        AGE =root.findViewById(R.id.editTextTextPersonName3);
        EMAILID =root.findViewById(R.id.editTextTextPersonName4);
        MOBILENO =root.findViewById(R.id.editTextTextPersonName5);
      //  PASSWORD =root.findViewById(R.id.editTextTextPersonName6);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile=snapshot.getValue(User.class);
                if(userprofile !=null)
                {
                    String fullName=userprofile.Name;
                    String age=userprofile.Age;
                    String emailis=userprofile.Emailid;
                    String mobileno=userprofile.MobileNo;
                  //  String password=userprofile.Password;

                    NAME.setText(fullName);
                    AGE.setText(age);
                    EMAILID.setText(emailis);
                    MOBILENO.setText(mobileno);
                //    PASSWORD.setText(password);




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               // Toast.makeText(HomeFragment.this, "Something wrong happened ! " , Toast.LENGTH_SHORT).show();
           //    Toast.makeText(HomeFragment.this, "something wrong happended!",Toast.LENGTH_SHORT);
            }
        });



        return root;


    }






}