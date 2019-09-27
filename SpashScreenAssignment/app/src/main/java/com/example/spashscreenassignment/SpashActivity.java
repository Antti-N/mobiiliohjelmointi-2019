package com.example.spashscreenassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpashActivity extends AppCompatActivity {

    EditText et;
    Button disable;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);


        et = findViewById(R.id.editText);
        disable = findViewById(R.id.disable);


        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.isEnabled()){
                    et.setEnabled(false);
                } else {
                    et.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}
