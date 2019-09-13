package com.example.layouttehtava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    private Button button1;
    private Button button2;
    private Button button3;
    private ImageView imageView;

    private boolean disabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buttons
        this.button1 = findViewById(R.id.button1);
        this.button2 = findViewById(R.id.button2);
        this.button3 = findViewById(R.id.button3);

        this.imageView = findViewById(R.id.imageView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Button1 functionality
                if (!disabled)
                {
                    disabled = true;
                } else {
                    disabled = false;
                }

                button2.setEnabled(disabled);
                button3.setEnabled(disabled);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Button2 functionality

                //if (imageView.isShown())
                //{
                    imageView.setVisibility(View.GONE);

                //}

            }
        });

        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }
}
