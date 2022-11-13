package com.example.basiclistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    private ListView listView;
    BottomNavigationView nav;

    public static ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Search Recipes");

        nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.add:
                        Intent intent1 = new Intent(getApplicationContext(), RecipeInput.class);
                        System.out.println("Here in the wrong area");
                        startActivity(intent1);

                    default:
                }

                return true;
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setUpData(
                    extras.getString("name"),
                    extras.getStringArray("ingredients"),
                    extras.getStringArray("tags"),
                    extras.getStringArray("directions")
            );
        }
        initSearchWidgets();
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

    public void setUpData(String name, String[] ingredients, String[] tags, String[] directions) {
        Recipe newRecipe = new Recipe(name, ingredients, tags, directions);
        recipeList.add(newRecipe);
    }

    public void setUpOnclickListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Recipe selectedRecipe = (Recipe) (listView.getItemAtPosition(position));
                System.out.println(selectedRecipe.getName());
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectedRecipe.getId());
                System.out.println("Here");
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