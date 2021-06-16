package com.example.healths.ui.home;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healths.R;
import com.example.healths.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
//
//    DatabaseReference reference;
//    FirebaseUser user;
//    String userID;
//    TextView NAME,AGE,EMAILID,MOBILENO,PASSWORD;
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("My Details");
//
//
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        userID=user.getUid();
//
//          NAME.findViewById(R.id.editTextTextPersonName2);
//        AGE  .findViewById(R.id.editTextTextPersonName3);
//        EMAILID .findViewById(R.id.editTextTextPersonName4);
//        MOBILENO  .findViewById(R.id.editTextTextPersonName5);
//        PASSWORD .findViewById(R.id.editTextTextPersonName6);
//
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userprofile=snapshot.getValue(User.class);
//                if(userprofile !=null)
//                {
//                    String fullName=userprofile.Name;
//                    String age=userprofile.Age;
//                    String emailis=userprofile.Emailid;
//                    String mobileno=userprofile.MobileNo;
//                    String password=userprofile.Password;
//
//                    NAME.setText(fullName);
//                    AGE.setText(age);
//                    EMAILID.setText(emailis);
//                    MOBILENO.setText(mobileno);
//                    PASSWORD.setText(password);
//
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Toast.makeText(HomeFragment.this, "Something wrong happened ! " , Toast.LENGTH_SHORT).show();
//                //    Toast.makeText(HomeFragment.this, "something wrong happended!",Toast.LENGTH_SHORT);
//            }
//        });

    }

    public LiveData<String> getText() {
        return mText;
    }
}