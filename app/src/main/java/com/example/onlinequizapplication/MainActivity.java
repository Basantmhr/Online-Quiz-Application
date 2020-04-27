package com.example.onlinequizapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import com.example.onlinequizapplication.Data.questionBank;
import com.example.onlinequizapplication.controller.AppController;

import java.time.LocalTime;



public class MainActivity extends AppCompatActivity {
    private ConstraintLayout cl;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDrawableDN();
        questionBank ql = new questionBank();
        ql.getQuestion();
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
