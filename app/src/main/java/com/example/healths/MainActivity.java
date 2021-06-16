package com.example.healths;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText NAME,AGE,EMAILID,MOBILENO,PASSWORD;
    Button SIGNUPBUTTON;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView=(TextView) findViewById(R.id.textView1);
        NAME=(EditText) findViewById(R.id.name);
        AGE=(EditText) findViewById(R.id.age);
        EMAILID=(EditText) findViewById(R.id.emailid);
        MOBILENO=(EditText) findViewById(R.id.mobileno);
        PASSWORD=(EditText) findViewById(R.id.password);
        SIGNUPBUTTON=(Button) findViewById(R.id.signupbutton);
        fAuth=FirebaseAuth.getInstance();
        progressBar=(ProgressBar) findViewById(R.id.progressBar);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();// will open another login activity
            }
        });

/**
 *
 * if the user login or register but din't signout
 * then automatically  login
 *
 */
        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),NAVS.class));
            finish();
        }



        SIGNUPBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=NAME.getText().toString().trim();
                String Age=AGE.getText().toString().trim();
                String email=EMAILID.getText().toString().trim();
                String Mobileno=MOBILENO.getText().toString().trim();
                String password=PASSWORD.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    EMAILID.setError("Email is required");
                    return ;
                }
                if(TextUtils.isEmpty(password))
                {
                    PASSWORD.setError("password is required");
                    return ;
                }

                if(password.length()<6){
                    PASSWORD.setError("password must be greater then 6 character");
                    return ;
                }

                progressBar.setVisibility(View.VISIBLE);

                // REGISTER THE USER'S IN THE FIREBASE
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                                 User us=new User(Name,Age,email,Mobileno,password);
                           FirebaseDatabase.getInstance().getReference("Users")
                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  .setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful())
                                   {
                                       Toast.makeText(MainActivity.this, "user created", Toast.LENGTH_SHORT).show();
                                          startActivity(new Intent(getApplicationContext(),NAVS.class));
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                   }
                               }
                           });


                        }


                    }
                });

            }
        });






    }

    private void openLogin()
    {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
}