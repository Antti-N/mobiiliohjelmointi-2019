package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editableText;
    private Button save;
    private Button done;
    private ArrayList<String> ShoppingList;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";



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
                if (text.length() > 3 && text.length() < 15)
                {

                    ShoppingList.add(text);
                    String tostText = "Lisätty listaan";
                    Toast toast = Toast.makeText(getApplicationContext(), tostText , Toast.LENGTH_SHORT);
                    toast.show();


                }

            }
        });

        this.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo
                Intent intent = new Intent(getApplicationContext(), ShowList.class);

                String text = "";

                if (ShoppingList.size() > 0)
                for (String item : ShoppingList) {

                    text += item + ", ";

                }

                intent.putExtra(EXTRA_MESSAGE, text);

                startActivity(intent);
            }

        });

    }




}
