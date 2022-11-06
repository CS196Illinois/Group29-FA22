package com.example.recipeapp;

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

    private static ArrayList<com.example.recipeapp.Recipe> recipeList = new ArrayList<com.example.recipeapp.Recipe>();
    private static ArrayList<com.example.recipeapp.Recipe> recipeHistory = new ArrayList<>();
    private ListView listView;


    public static ArrayList<com.example.recipeapp.Recipe> getRecipeList() {
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
        com.example.recipeapp.Recipe chicken_tikka_masala = new com.example.recipeapp.Recipe("0", "Chicken Tikka Masala");
        recipeList.add(chicken_tikka_masala);
        chicken_tikka_masala.addTag("Indian");
        chicken_tikka_masala.addTag("Spicy");

        com.example.recipeapp.Recipe clam_chowder = new com.example.recipeapp.Recipe("1", "Clam Chowder");
        recipeList.add(clam_chowder);

        com.example.recipeapp.Recipe split_pea_soup = new com.example.recipeapp.Recipe("2", "Split Pea Soup");
        recipeList.add(split_pea_soup);

        com.example.recipeapp.Recipe hamburgers = new com.example.recipeapp.Recipe("3", "Hamburgers");
        recipeList.add(hamburgers);

        com.example.recipeapp.Recipe german_chocolate_cake = new com.example.recipeapp.Recipe("4", "German Chocolate Cake");
        recipeList.add(german_chocolate_cake);
        german_chocolate_cake.addTag("Dessert");
        german_chocolate_cake.addTag("Cake");
    }

    public void setUpOnclickListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                com.example.recipeapp.Recipe selectedRecipe = (com.example.recipeapp.Recipe) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), com.example.recipeapp.DetailActivity.class);
                showDetail.putExtra("id", selectedRecipe.getId());
                startActivity(showDetail);
            }
        });

    }

    public void setUpList() {
        listView = (ListView) findViewById(R.id.recipesListView);

        com.example.recipeapp.RecipeAdapter adapter = new com.example.recipeapp.RecipeAdapter(getApplicationContext(), 0, recipeList);
        listView.setAdapter(adapter);
    }

    public static void addHistory(Recipe r) {
        recipeHistory.add(r);
    }

    public static void removeHistory(Recipe r) {
        recipeHistory.remove(r);
    }

}