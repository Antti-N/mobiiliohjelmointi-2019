package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Date> dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.addBtn);
        ListView lw = (ListView)findViewById(R.id.lw);

        dates = new ArrayList<Date>();

        final DateAdapter adapter = new DateAdapter(this, R.layout.row, dates);
        lw.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates.add(new Date());
                adapter.notifyDataSetChanged();
            }
        });

    }
}
