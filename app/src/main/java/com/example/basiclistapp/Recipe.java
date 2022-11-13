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
