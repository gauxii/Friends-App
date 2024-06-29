package com.example.friendsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
AppCompatButton b1;
String apiurl="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.friendname);
        ed3=(EditText) findViewById(R.id.friendnick);
        ed4=(EditText) findViewById(R.id.describe);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName=ed1.getText().toString();
                String getfriendname=ed2.getText().toString();
                String getfriendnick=ed3.getText().toString();
                String describe=ed4.getText().toString();
                //creating JSON object
                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName",getfriendname);
                    friend.put("friendNickName",getfriendnick);
                    friend.put("DescribeYourFriend",describe);
                }
                catch(JSONException e)
                {
                    throw new RuntimeException(e);
                }
                //JSON object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}