package com.example.onlinequizapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onlinequizapplication.MainActivity;
import com.example.onlinequizapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserDashboardActivity extends AppCompatActivity {
    private AppCompatButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        logout=findViewById(R.id.btn_logout_dashboard);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateLogOut(view);
            }
        });



    }
    public void navigateLogOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
