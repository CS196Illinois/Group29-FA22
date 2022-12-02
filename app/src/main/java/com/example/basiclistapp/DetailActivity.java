package com.example.basiclistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Recipe selectedRecipe;
    Button favoriteButton;
    String parsedStringID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Recipe View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSelectedRecipe();
        setValues();

        favoriteButton = (Button) findViewById(R.id.favoriteButton);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HERE IS THE BUTTON CLICK");
                Intent intent = new Intent(getApplicationContext(), Favorites.class);
                intent.putExtra("name", selectedRecipe.getName());
                intent.putExtra("ingredients", selectedRecipe.getIngredientsArray());
                intent.putExtra("tags", selectedRecipe.getTagsArray());
                intent.putExtra("directions", selectedRecipe.getDirectionsArray());
                System.out.println(selectedRecipe.getName());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getSelectedRecipe() {
        Intent previousIntent = getIntent();
        parsedStringID = previousIntent.getStringExtra("id");
        selectedRecipe = MainActivity.getRecipeList().get(Integer.valueOf(parsedStringID));
    }

    public void setValues() {
        TextView tv1 = (TextView) findViewById(R.id.recipeName);
        TextView tv2 = (TextView) findViewById(R.id.recipeTags);
        tv1.setText(selectedRecipe.getName());
        tv2.setText((selectedRecipe.getTags()));
    }

}