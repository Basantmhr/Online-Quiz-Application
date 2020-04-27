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
import java.util.List;


public class questionBank {
    ArrayList  <questionModel> question = new ArrayList<>();
    private String url = "https://opentdb.com/api.php?amount=15&category=22&difficulty=hard&type=multiple";
    public List getQuestion(){
          JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    JSONObject jso = jsonArray.getJSONObject(0);
                    Log.d("DataBack", "onResponse: " + jso.getString("question") + jso.getJSONArray("incorrect_answers"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", "onErrorResponse:  "+error);

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    return (List)null;
    }
}
