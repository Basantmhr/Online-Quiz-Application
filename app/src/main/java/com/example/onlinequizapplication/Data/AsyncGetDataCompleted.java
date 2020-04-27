package com.example.onlinequizapplication.Data;

import com.example.onlinequizapplication.Model.questionModel;

import java.util.ArrayList;

public interface AsyncGetDataCompleted {
    void onRecievedSuccess(ArrayList<questionModel> qm);
}
