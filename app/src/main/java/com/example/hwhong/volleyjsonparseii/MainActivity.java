package com.example.hwhong.volleyjsonparseii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String JSONArray = "http://api.androidhive.info/volley/person_array.json";

    private ListView listView;
    private Button parse, display;
    private ListAdapter adapter;

    private RequestQueue queue;

    private String[] names, emails, homes, mobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new String[2];
        emails = new String[2];
        homes = new String[2];
        mobiles = new String[2];

        parse = (Button) findViewById(R.id.parse);
        display = (Button) findViewById(R.id.display);

        listView = (ListView) findViewById(R.id.listView);
        queue = Volley.newRequestQueue(getApplicationContext());
        adapter = new ListAdapter(getApplicationContext(), names, emails, homes, mobiles);

        parse.setOnClickListener(this);
        display.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.parse:

                JsonArrayRequest objectRequest = new JsonArrayRequest(JSONArray, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.d("response", response.toString());

                                try {
                                    // Parsing json array response
                                    // loop through each json object

                                    for (int i = 0; i < response.length(); i++) {

                                        JSONObject person = (JSONObject) response.get(i);

                                        String name = person.getString("name");
                                        String email = person.getString("email");
                                        JSONObject phone = person
                                                .getJSONObject("phone");
                                        String home = phone.getString("home");
                                        String mobile = phone.getString("mobile");

                                        names[i] = "Name: " + name;
                                        emails[i] = "Email: " + email;
                                        homes[i] = "Home: " + home;
                                        mobiles[i] = "Mobile: " + mobile;

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("response", "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(objectRequest);
                Toast.makeText(getApplicationContext(), "JSON Successfully Parsed", Toast.LENGTH_SHORT).show();

                break;

            case R.id.display:
                listView.setAdapter(adapter);
                break;

        }

    }
}
