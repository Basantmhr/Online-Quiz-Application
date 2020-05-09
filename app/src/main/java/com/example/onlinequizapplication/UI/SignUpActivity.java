package com.example.onlinequizapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.onlinequizapplication.MainActivity;
import com.example.onlinequizapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private final static String TAG = SignUpActivity.class.getSimpleName();
    private FirebaseAuth mAuth ;
    private AppCompatButton signUpButton;
    private String userString, passwordString;
    private AppCompatEditText mEmail , mPassword ,mName;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        mEmail=(AppCompatEditText)findViewById(R.id.signUpUserMail);
        mPassword=(AppCompatEditText)findViewById(R.id.signUpPassword);
        signUpButton=findViewById(R.id.signUpTrue);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userString=mEmail.getText().toString().trim();
                passwordString=mPassword.getText().toString().trim();
               signUpUser(userString,passwordString);
            }
        });



    }
    void signUpUser(String userString,String passwordString)
    {
        Log.d(TAG, "signUpUser: "+userString+"dd"+passwordString);
     if(TextUtils.isEmpty(userString))
    {
        Toast.makeText(this,"Please Enter UserName. ", Toast.LENGTH_SHORT).show();
        return;
    }
    if(TextUtils.isEmpty(passwordString))
    {
        Toast.makeText(this,"Please Enter Password. ", Toast.LENGTH_SHORT).show();
        return;
    }
    if(passwordString.length()<6)
    {
        Toast.makeText(this,"Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
        return;
    }
        mAuth.createUserWithEmailAndPassword(userString,passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("signup", "onComplete: "+"true");
                    updateUI(user);
                }
                else
                {   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }
    void updateUI(FirebaseUser user)
    {
        Intent I = new Intent(getApplicationContext() , MainActivity.class);
        startActivity(I);
    }
}
