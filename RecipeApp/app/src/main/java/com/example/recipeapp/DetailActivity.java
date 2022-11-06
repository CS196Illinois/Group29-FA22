package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.recipeapp.MainActivity;

public class DetailActivity extends AppCompatActivity {

    com.example.recipeapp.Recipe selectedRecipe;
    CheckBox ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Recipe View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSelectedShape();
        setValues();

        ch = (CheckBox) findViewById(R.id.recipe_made);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked){
                    selectedRecipe.setRecipeMade(true);
                    MainActivity.addHistory(selectedRecipe);
                    //System.out.println("checked");
                }
                else{
                    selectedRecipe.setRecipeMade(false);
                    MainActivity.removeHistory(selectedRecipe);
                    //System.out.println("unchecked");
                }
            }
        });
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