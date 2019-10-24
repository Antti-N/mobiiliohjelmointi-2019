package com.example.jsonassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public String url;
    RequestQueue que;
    ArrayList<JSONObject> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap


        final Network network = new BasicNetwork(new HurlStack());


        que = new RequestQueue(cache, network);


        que.start();

        Button get = (Button) findViewById(R.id.getBtn);
        ListView lw = (ListView) findViewById(R.id.listView);

        data = new ArrayList<JSONObject>();

        final ObjectAdapter adapter = new ObjectAdapter(this, R.layout.row, data);
        lw.setAdapter(adapter);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    parse();
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Connection not avaible";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

    }

    public void parse() {
        url = "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                data.removeAll(data);
                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        data.add(response.getJSONObject(i));
                    } catch (Exception e) {
                        System.out.println("Jotain meni nyt pieleen" + e.getMessage());
                    }
                }
                Log.e("toimii", "joo");
                //adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        que.add(req);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
