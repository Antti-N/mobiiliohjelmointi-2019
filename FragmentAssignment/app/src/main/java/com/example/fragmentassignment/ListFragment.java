package com.example.fragmentassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */

public class ListFragment extends Fragment {

    ObjectAdapter adapter;
    ArrayList<JSONObject> data;
    ListView lv;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        lv = rootView.findViewById(R.id.lv);
        data = new ArrayList<JSONObject>();


        adapter = new ObjectAdapter(getContext(), R.layout.row, data);
        lv.setAdapter(adapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    /*
        Otetaan vastaan datasetti mainita
        En tiedä miksi piti uudelleen loopata koko saapuva data tämän luokan dataan
        this.data = data, ei alla olevassa siis toiminut
     */
    public void getDataSetFromMain(ArrayList<JSONObject> data) {

        //Tyhjennetään vanha lista
        this.data.removeAll(this.data);


        for (JSONObject o : data) {
            this.data.add(o);
        }

        adapter.notifyDataSetChanged();

    }

}
