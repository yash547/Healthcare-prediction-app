package com.example.healths.ui.HISTORY;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healths.R;
import com.example.healths.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference myRef=null;

    private HistoryViewModel mViewModel;
RecyclerView recyclerView;
    Myadapter adapter;

    FirebaseUser fuser;
    String userids="";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.history_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_History);
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });

        recyclerView=root.findViewById(R.id.userList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        processsearch();


        return  root;
    }
    private void processsearch()
    {

        fuser= FirebaseAuth.getInstance().getCurrentUser();

        userids=fuser .getUid();

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("predictedDisease");

        myRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){

                List<Users> listRes = new ArrayList<>();

                for (DataSnapshot dataValues : dataSnapshot.getChildren())
                {
                   Users restaurantModel = dataValues.getValue(Users.class);

                   listRes.add(restaurantModel);
                }
                /**
                 *
                 * for testing purpose toast
                 *
                 */
             //   Toast.makeText(getContext(),"TaskTitle = "+listRes.get(0).getUSERID(),Toast.LENGTH_LONG).show();

                ArrayList<Users> arrayList=new ArrayList<Users>();
                for (int i=0;i<listRes.size();i++)
                {
                    if(userids.equals(listRes.get(i).getUSERID()))
                    {
                        arrayList.add(listRes.get(i));

                    }
                }
                adapter=new  Myadapter(arrayList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




}

}