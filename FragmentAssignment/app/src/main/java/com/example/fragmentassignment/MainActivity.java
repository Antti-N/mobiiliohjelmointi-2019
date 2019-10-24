package com.example.fragmentassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonFragment.ButtonClickListener {
    String url;
    ListFragment listFragment;
    ButtonFragment buttonFragment;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragmentit ja tietokanta
        databaseHelper = new DatabaseHelper(this);
        listFragment = new ListFragment();
        buttonFragment = new ButtonFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment2, buttonFragment)
                .replace(R.id.fragment, listFragment)
                .commit();


    }

    @Override
    public void onGetClicked() {
        // Tehdään http get request;
       makeRequest(new VolleyCallBack() {
           @Override
           public void onSuccess(ArrayList<JSONObject> response) {
               //listFragment.getDataSetFromMain(response);

               // Dropataan tässä tietokanna taulusta kaikki data ettei tule liikaa
                databaseHelper.dropAllData();

                // Lisätään rivi kerrallaan kantaan
               // En saanut koko listan kerralla lisäävää toimimaan
                for (JSONObject item : response) {
                    try {
                        databaseHelper.addRow(item.getString("nimi"), item.getString("pvm"));
                        Log.d("Läpi", "meni");
                    } catch(Exception e)
                    {
                        Log.d("Virhe: ", e.getMessage());
                    }
                }

                //tulostellaan toasti käyttäjälle
               CharSequence text = "Lisätty tietokantaan";
               Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
               toast.show();


           }
       });
    }

    @Override
    public void onShowClicked() {

        // Napsastaan data kannasta
        Cursor rows = databaseHelper.getData();

        // Parsitaan jsonobjecteiksi
        ArrayList<JSONObject> jsonRows = new ArrayList<JSONObject>();
        while (rows.moveToNext()) {
            JSONObject object = new JSONObject();

            try {
                object.put("nimi", rows.getString(1));
                object.put("pvm", rows.getString(2));
            } catch (Exception e ) {
                Log.d("Virhe: ", "Jotain meni pieleen");
            }
            jsonRows.add(object);
        }

        // Lähetään listview Fragmentille
        listFragment.getDataSetFromMain(jsonRows);

    }

    // Onnistumisen interfance kutsulle
    public interface VolleyCallBack {
        void onSuccess(ArrayList<JSONObject> callback);
    }


    // Tehdään volley request koijun urliin
    public void makeRequest(final VolleyCallBack callback) {
        url = "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";

        RequestQueue queue = Volley.newRequestQueue(this);



        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        // Parsitaan vähän saapuvaa responsea
                        ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                objects.add(response.getJSONObject(i));
                            } catch (Exception e) {
                                Log.d("Virhe: ", e.getMessage());
                            }
                        }
                        callback.onSuccess(objects);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}
