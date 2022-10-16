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

        getSelectedShape();
        setValues();

    }

    public void getSelectedShape() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedRecipe = MainActivity.getRecipeList().get(Integer.valueOf(parsedStringID));
    }

    public void setValues() {
        TextView tv = (TextView) findViewById(R.id.recipeName);

        tv.setText(selectedRecipe.getName());
    }

}