package com.example.basiclistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RecipeInput extends AppCompatActivity {

    private String name;
    private String[] ingredients, tags, directions;
    EditText nameInput, ingredientsInput, tagsInput, directionsInput;
    Button submitButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        nameInput = (EditText) findViewById(R.id.nameInput);
        ingredientsInput = (EditText) findViewById(R.id.ingredientsInput);
        tagsInput = (EditText) findViewById(R.id.tagsInput);
        directionsInput = (EditText) findViewById(R.id.directionsInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameInput.getText().toString();
                ingredients = ingredientsInput.getText().toString().trim().split("\n");
                tags = tagsInput.getText().toString().trim().split(",");
                directions = directionsInput.getText().toString().trim().split("\n");
            }
        });

    }
}