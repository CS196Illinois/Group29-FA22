package com.example.basiclistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private ListView listView;


    public static ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("My Recipes");

        initSearchWidgets();
        setUpData();
        setUpList();
        setUpOnclickListener();

    }

    private void initSearchWidgets() {
        SearchView searchView = (SearchView) findViewById(R.id.recipeListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
                for (Recipe r: recipeList) {
                    if (r.getName().toLowerCase().contains(s.toLowerCase()) || r.getTags().toLowerCase().contains(s.toLowerCase())) {
                         filteredRecipes.add(r);
                    }
                }

                RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), 0, filteredRecipes);
                listView.setAdapter(adapter);

                return false;
            }
        });
    }

    public void setUpData() {
        Recipe chicken_tikka_masala = new Recipe("0", "Chicken Tikka Masala");
        recipeList.add(chicken_tikka_masala);
        chicken_tikka_masala.addTag("Indian");
        chicken_tikka_masala.addTag("Spicy");

        Recipe clam_chowder = new Recipe("1", "Clam Chowder");
        recipeList.add(clam_chowder);

        Recipe split_pea_soup = new Recipe("2", "Split Pea Soup");
        recipeList.add(split_pea_soup);

        Recipe hamburgers = new Recipe("3", "Hamburgers");
        recipeList.add(hamburgers);

        Recipe german_chocolate_cake = new Recipe("4", "German Chocolate Cake");
        recipeList.add(german_chocolate_cake);
        german_chocolate_cake.addTag("Dessert");
        german_chocolate_cake.addTag("Cake");
    }

    public void setUpOnclickListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Recipe selectedRecipe = (Recipe) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectedRecipe.getId());
                startActivity(showDetail);
            }
        });

    }

    public void setUpList() {
        listView = (ListView) findViewById(R.id.recipesListView);

        RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), 0, recipeList);
        listView.setAdapter(adapter);
    }

}