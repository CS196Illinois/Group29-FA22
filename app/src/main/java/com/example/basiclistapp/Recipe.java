package com.example.basiclistapp;

import java.util.ArrayList;

public class Recipe {
    private String id;
    private String name;
    private ArrayList<String> tags = new ArrayList<String>();
    private ArrayList<String> ingredients = new ArrayList<String>();
    private ArrayList<String> directions = new ArrayList<String>();
    private static int idNum = 0;


    public Recipe(String name, String[] ingredients, String[] tags, String[] directions) {
        this.id = String.valueOf(idNum);
        idNum ++;
        this.name = name;
        this.tags = toList(tags);
        this.ingredients = toList(ingredients);
        this.directions = toList(directions);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        String ret = "       Ingredients:";
        for (String ing : this.ingredients) {
            ret += "\n    >" + ing;
        }
        return ret;
    }

    public String getDirections() {
        String ret = "       Directions:";
        for (String ing : this.directions) {
            ret += "\n    >" + ing;
        }
        return ret;
    }

    public String[] getIngredientsArray() {
        String[] ret = new String[ingredients.size()];
        for (int i = 0; i < ingredients.size(); i++) {
            ret[i] = ingredients.get(i);
        }
        return ret;
    }


    public String[] getDirectionsArray() {
        String[] ret = new String[directions.size()];
        for (int i = 0; i < directions.size(); i++) {
            ret[i] = directions.get(i);
        }
        return ret;
    }

    public String[] getTagsArray() {
        String[] ret = new String[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            ret[i] = tags.get(i);
        }
        return ret;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public String getTags() {
        String ret = "Tags: ";
        for (int i = 0; i < tags.size(); i++) {
            if (i == tags.size() - 1) {
                ret += tags.get(i).trim();
                break;
            }
            ret += tags.get(i).trim() + ", ";
        }

        return ret;
    }

    public ArrayList<String> toList(String[] array) {
        ArrayList<String> ret = new ArrayList<>();
        for (String item : array) {
            ret.add(item);
        }
        return ret;
    }

}
