package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {

    private static ArrayList<Recipe> recipeHistory = new ArrayList<>();
    BottomNavigationView nav;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");

        listView = findViewById(R.id.historyListView);
        RecipeAdapter adapter = new RecipeAdapter(getApplicationContext(), 0, recipeHistory);
        listView.setAdapter(adapter);

        nav = findViewById(R.id.nav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int newItem = item.getItemId();
                if (newItem == R.id.add) {
                    Intent intent1 = new Intent(getApplicationContext(), RecipeInput.class);
                    startActivity(intent1);
                } else if (newItem == R.id.search) {
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);
                }

                return true;
            }
        });
        setUpOnclickListener();
    }

    public void setUpOnclickListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Recipe selectedRecipe = (Recipe) (listView.getItemAtPosition(position));
                //System.out.println(selectedRecipe.getName());
                Intent showDetail = new Intent(getApplicationContext(), DetailActivity.class);
                showDetail.putExtra("id", selectedRecipe.getId());
                System.out.println("Here");
                startActivity(showDetail);
            }
        });

    }


    public static void addHistory(Recipe r) {
        recipeHistory.add(r);
    }

    public static void removeHistory(Recipe r) {
        recipeHistory.remove(r);
    }

}
