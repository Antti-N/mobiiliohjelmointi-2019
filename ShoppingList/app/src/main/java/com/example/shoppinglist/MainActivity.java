package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editableText;
    private Button save;
    private Button done;
    private ArrayList<String> ShoppingList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // uKäyttöliittymä komponentit
        this.save = findViewById(R.id.save);
        this.done = findViewById(R.id.done);
        this.editableText = findViewById(R.id.edit_text_input);

        // Lista komponentti "tuotteille"
        this.ShoppingList = new ArrayList<String>();

        this.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = editableText.getText().toString();
                // Todo
                if (text.length() < 3 && text.length() < 15)
                {
                    ShoppingList.add(text);

                }

            }
        });

        this.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo
            }

        });

    }




}
