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
    boolean etState = true;
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
                    etState = false;
                } else {
                    et.setEnabled(true);
                    etState = true;
                }
            }
        });

        if (savedInstanceState != null) {
            boolean state = savedInstanceState.getBoolean("etState");
            et.setEnabled(state);
            et.setText(text);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("etState", etState);
        outState.putString("text", et.getText().toString());
    }
}
