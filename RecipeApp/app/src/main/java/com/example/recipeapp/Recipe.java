package com.example.recipeapp;

import java.util.ArrayList;

public class Recipe {
    private String id;
    private String name;
    private ArrayList<String> tags = new ArrayList<String>();
    private ArrayList<String> ingredients = new ArrayList<String>();
    private ArrayList<String> directions = new ArrayList<String>();
    private boolean recipeMade;

    public Recipe(String id, String name) {
        this.id = id;
        this.name = name;
        recipeMade = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
                ret += tags.get(i);
                break;
            }
            ret += tags.get(i) + ", ";
        }

        return ret;
    }

    public void setRecipeMade(boolean b) {
        recipeMade = b;
    }

    public boolean getRecipeMade() {
        return recipeMade;
    }

}