package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

class DateAdapter extends ArrayAdapter<Date> {

    Context context;
    ArrayList<Date> dates;
    int resource;

    public DateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Date> objects) {
        super(context, resource, objects);
        this.dates = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(context);
        convertView = i.inflate(resource, parent, false);

        Button remove = convertView.findViewById(R.id.rmBtn);
        TextView tv = convertView.findViewById(R.id.dateText);

        tv.setText(dates.get(position).toString());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates.remove(getItem(position));
                notifyDataSetChanged();
            }
        });


        return convertView;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
