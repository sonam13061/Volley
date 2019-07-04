package com.example.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2;
    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        img = findViewById(R.id.imageView);
        //callVolley();
        callJsonObjectR();
    }

    private void callVolley(){
        String url = "https://www.google.com/";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txt.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText("error : "+error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private  void callJsonObjectR(){
        String url = "https://api.github.com/users/cmpundhir";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String username = response.getString("login");
                    long id = response.getLong("id");
                    t1.setText(username);
                    t2.setText(id+"");
                    String imhgUrl = response.getString("avatar_url");
                    Glide.with(MainActivity.this).load(imhgUrl).into(img);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                txt.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setText("error : "+error);
            }
        });
        Volley.newRequestQueue(this).add(request);
    }

}
