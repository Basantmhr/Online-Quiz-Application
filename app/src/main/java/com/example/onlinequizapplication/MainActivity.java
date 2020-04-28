package com.example.onlinequizapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.onlinequizapplication.Data.AsyncGetDataCompleted;
import com.example.onlinequizapplication.Data.questionBank;
import com.example.onlinequizapplication.Model.questionModel;
import com.example.onlinequizapplication.UI.activity_sign_up;
import com.example.onlinequizapplication.controller.AppController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ConstraintLayout cl;
    private AppCompatButton signUpButton;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpButton=findViewById(R.id.signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , activity_sign_up.class);
                startActivity(i);
            }
        });

        List <questionModel> mQuestionModelArrayList= new questionBank().getQuestion(new AsyncGetDataCompleted() {
            @Override
            public void onRecievedSuccess(ArrayList<questionModel> qm) {
                Log.d("Main", "onRecievedSuccess: "+qm);

            }
        });
        //setDrawableDN();

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
