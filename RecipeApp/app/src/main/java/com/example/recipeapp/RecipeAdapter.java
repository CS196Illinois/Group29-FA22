package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<com.example.recipeapp.Recipe> {

    public RecipeAdapter(Context context, int resource, List<com.example.recipeapp.Recipe> recipeList) {
        super(context, resource, recipeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.recipeapp.Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_cell, parent, false);
        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.recipeName);
        TextView tv2 = (TextView) convertView.findViewById(R.id.recipeTags);

        tv1.setText(recipe.getName());
        tv2.setText(recipe.getTags());

        return convertView;
    }
}