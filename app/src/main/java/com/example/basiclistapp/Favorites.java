package com.example.basiclistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    private static ArrayList<Recipe> favoriteList = new ArrayList<Recipe>();
    private ListView favoriteListView;
    BottomNavigationView nav;

    public static ArrayList<Recipe> getRecipeList() {
        return favoriteList;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("HERE IN THE FAVORITES CONSTRUCTOR");

        setContentView(R.layout.activity_favorites);
        getSupportActionBar().setTitle("Favorite Recipes");

        System.out.println("HERE IN THE FAVORITES CONSTRUCTOR FURTHER DOWN");


        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.add) {
                    Intent goToAdd = new Intent(getApplicationContext(), RecipeInput.class);
                    startActivity(goToAdd);
                }
                else if (item.getItemId() == R.id.search) {
                    Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToMain);
                }

                return true;
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            System.out.println("HERE");
            setUpData(
                    extras.getString("name"),
                    extras.getStringArray("ingredients"),
                    extras.getStringArray("tags"),
                    extras.getStringArray("directions")
            );
        }
        initSearchWidgets();
        setUpList();
    }

    private void initSearchWidgets() {
        SearchView searchView = (SearchView) findViewById(R.id.favoritesListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();
                for (Recipe r: favoriteList) {
                    if (r.getName().toLowerCase().contains(s.toLowerCase()) || r.getTags().toLowerCase().contains(s.toLowerCase())) {
                        filteredRecipes.add(r);
                    }
                }

                RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), 0, filteredRecipes);
                favoriteListView.setAdapter(adapter);

                return false;
            }
        });
    }

    public void setUpData(String name, String[] ingredients, String[] tags, String[] directions) {
        Recipe newRecipe = new Recipe(name, ingredients, tags, directions);
        System.out.println("HERE SETTING UP DATA");
        favoriteList.add(newRecipe);
    }


    public void setUpList() {
        favoriteListView = (ListView) findViewById(R.id.favoritesListView);

        RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), 0, favoriteList);
        favoriteListView.setAdapter(adapter);
    }
}