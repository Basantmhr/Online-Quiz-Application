package com.example.onlinequizapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.onlinequizapplication.Data.AsyncGetDataCompleted;
import com.example.onlinequizapplication.Data.QuestionBank;
import com.example.onlinequizapplication.Model.QuestionModel;
import com.example.onlinequizapplication.UI.SignUpActivity;
import com.example.onlinequizapplication.UI.UserDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ConstraintLayout cl;
    private AppCompatButton signInButton;
    private AppCompatButton signUpButton;
    private FirebaseAuth mAuth;
    private String userString, passwordString;
    private AppCompatEditText mEmail , mPassword;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        signInButton=findViewById(R.id.signIn);
        signUpButton=findViewById(R.id.signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpPage = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signUpPage);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail=findViewById(R.id.signInUser);
                mPassword=findViewById(R.id.signInPassword);
                userString=mEmail.getText().toString();
                passwordString=mPassword.getText().toString();
               signIn(userString,passwordString);
            }
        });

        List <QuestionModel> mQuestionModelArrayList= new QuestionBank().getQuestion(new AsyncGetDataCompleted() {
            @Override
            public void onRecievedSuccess(ArrayList<QuestionModel> qm) {
                Log.d("Main", "onRecievedSuccess: "+qm);

            }
        });
        //setDrawableDN();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    private void  signIn(String userString,String passwordString)
    {
    mAuth.signInWithEmailAndPassword(userString,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful())
            {
                FirebaseUser user = mAuth.getCurrentUser() ;
                updateUI(user);
            }
            else
            {
                Log.d("Fail", "onComplete: " +"err");
                Toast.makeText(getApplicationContext(),"Create Account ",Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
    void updateUI(FirebaseUser user)
    {
        Intent i = new Intent(this , UserDashboardActivity.class);
        startActivity(i);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setDrawableDN()
    {   cl=(ConstraintLayout)findViewById(R.id.mainActivityLayout);
        LocalTime hr = LocalTime.now();
        int intHr=hr.getHour();
        Log.d("Hour", "setDrawableDN: "+intHr);

        if(intHr>4 && intHr<18)
        {
            cl.setBackground(getDrawable(R.drawable.background_design_day));
        }
        else
            cl.setBackground(getDrawable(R.drawable.background_design_night));


    }

}
