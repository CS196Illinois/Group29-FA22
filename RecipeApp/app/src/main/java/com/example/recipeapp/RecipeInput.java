package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import java.util.ArrayList;

public class RecipeInput extends AppCompatActivity {

    private String name;
    private String[] ingredients, tags, directions;
    EditText nameInput, ingredientsInput, tagsInput, directionsInput;
    Button submitButton;
    BottomNavigationView nav;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        getSupportActionBar().setTitle("New Recipe");
        nav = findViewById(R.id.nav2);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int newItem = item.getItemId();
                if (newItem == R.id.search) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                } else if (newItem == R.id.history) {
                    Intent intent2 = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent2);
                }

                return true;
            }
        });


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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("ingredients", ingredients);
                intent.putExtra("tags", tags);
                intent.putExtra("directions", directions);
                startActivity(intent);
            }
        });

    }
}
