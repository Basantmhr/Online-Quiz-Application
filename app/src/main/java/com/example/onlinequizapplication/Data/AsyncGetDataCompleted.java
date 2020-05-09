package com.example.onlinequizapplication.Data;

import com.example.onlinequizapplication.Model.QuestionModel;

import java.util.ArrayList;

public interface AsyncGetDataCompleted {
    void onRecievedSuccess(ArrayList<QuestionModel> qm);
}
