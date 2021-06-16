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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {
TextView textView;

    EditText emails,passwords;
    Button button;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.textView2);
        emails = (EditText) findViewById(R.id.EMAILID);
        passwords = (EditText) findViewById(R.id.PASSWORD);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        fAuth = FirebaseAuth.getInstance();

        button = (Button) findViewById(R.id.btn1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUP();
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=emails.getText().toString().trim();
                String Password=passwords.getText().toString().trim();

                if(TextUtils.isEmpty(Email))
                {
                    emails.setError("Email is required");
                    return ;
                }
                if(TextUtils.isEmpty(Password))
                {
                    passwords.setError("password is required");
                    return ;
                }

                if(Password.length()<6){
                    passwords.setError("password must be greater then 6 character");
                    return ;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Aunticate the user

                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });
        
    }

    private void openSignUP()
    {
        Intent intents=new Intent(this,MainActivity.class);
        startActivity(intents);
    }
}