package com.example.onlinequizapplication.Data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.onlinequizapplication.Model.questionModel;
import com.example.onlinequizapplication.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class questionBank {
    ArrayList  <questionModel> questionModelArrayList = new ArrayList<>();
    private String url = "https://opentdb.com/api.php?amount=15&category=22&difficulty=hard&type=multiple";
    private ArrayList <String> mString = new ArrayList<>();
    public List getQuestion(final AsyncGetDataCompleted callback){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
//                    JSONArray jsonArray = response.getJSONArray("results"); //For debug
//                    JSONObject jso = jsonArray.getJSONObject(0); //For debug
//                    Log.d("DataBack", "onResponse: " + jso.getString("question") + jso.getJSONArray("incorrect_answers")); //For debug
                    for(int i=0;i<response.getJSONArray("results").length();i++) {
//                         Log.d("Ques:", "onResponse: "+response.getJSONArray("results").getJSONObject(i).getJSONArray("incorrect_answers").getString(0)); //For debug
                        questionModel mQuestion = new questionModel();
                        mQuestion.setQuestion(response.getJSONArray("results").getJSONObject(i).getString("question"));

                        mString.add(response.getJSONArray("results").getJSONObject(i).getString("correct_answer"));
                        mString.add(response.getJSONArray("results").getJSONObject(i).getJSONArray("incorrect_answers").getString(0));
                        mString.add(response.getJSONArray("results").getJSONObject(i).getJSONArray("incorrect_answers").getString(1));
                        mString.add(response.getJSONArray("results").getJSONObject(i).getJSONArray("incorrect_answers").getString(2));
                        Collections.shuffle(mString);
                        mQuestion.setOption1(mString.get(0));
                        mQuestion.setOption2(mString.get(1));
                        mQuestion.setOption3(mString.get(2));
                        mQuestion.setOption4(mString.get(3));
                        mQuestion.setOptionTrue(response.getJSONArray("results").getJSONObject(i).getString("correct_answer"));
                        questionModelArrayList.add(mQuestion);
                        //Log.d("Qm", "onResponse: "+mQuestion);
                        mString.clear();

                    }
                   // Log.d("total", "onResponse: "+response.getJSONArray("results")); //For debug

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(callback!=null)
                {
                    callback.onRecievedSuccess(questionModelArrayList);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "onErrorResponse:  "+error);

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        return questionModelArrayList;
    }
}
