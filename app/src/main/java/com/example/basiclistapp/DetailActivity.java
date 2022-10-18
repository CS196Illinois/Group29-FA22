package com.example.basiclistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Recipe selectedRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Recipe View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSelectedShape();
        setValues();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getSelectedShape() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedRecipe = MainActivity.getRecipeList().get(Integer.valueOf(parsedStringID));
    }

    public void setValues() {
        TextView tv1 = (TextView) findViewById(R.id.recipeName);
        TextView tv2 = (TextView) findViewById(R.id.recipeTags);
        tv1.setText(selectedRecipe.getName());
        tv2.setText((selectedRecipe.getTags()));
    }

}