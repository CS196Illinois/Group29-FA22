package com.example.basiclistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(Context context, int resource, List<Recipe> recipeList) {
        super(context, resource, recipeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = getItem(position);

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
