package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

public class RecipeBook implements Writable {
    private ArrayList<Recipe> myRecipes;
    private String title;
    private String author;
    private static EventLog events;

    public RecipeBook(String title, String author) {
        myRecipes = new ArrayList<>();
        this.title = title;
        this.author = author;
        events = EventLog.getInstance();
    }

    // EFFECTS: returns the title of the recipe book
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns the name of the author
    public String getAuthor() {
        return author;
    }

    // REQUIRES: size >= 0
    // EFFECTS: returns number of recipes in the recipe book
    public int getNumberOfRecipes() {
        return myRecipes.size();
    }

    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe book
    public void addRecipe(Recipe r) {
        events.logEvent(new Event("Recipe '" + r.getName() + "' has been added"));
        myRecipes.add(r);
    }

    // MODIFIES: this
    // EFFECTS: removes the given recipe from the recipe book and returns true, false otherwise
    public boolean removeRecipe(Recipe r) {
        events.logEvent(new Event("Recipe '" + r.getName() + "' has been removed"));
        return myRecipes.remove(r);
    }

    // MODIFIES: this
    // EFFECTS: removes all the recipes with the given recipe name
    //          from the recipe book and returns true, false otherwise
    public boolean removeRecipe(String recipeName) {
        events.logEvent(new Event("Recipe '" + recipeName + "' has been removed"));
        for (Recipe r:myRecipes) {
            if (recipeName.equals(r.getName())) {
                myRecipes.remove(r);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: searches for the given recipe name, and returns the recipes that contains the given name
    public ArrayList<Recipe> searchRecipe(String recipeName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r:myRecipes) {
            if (r.getName().contains(recipeName)) {
                recipes.add(r);
            }
        }
        events.logEvent(new Event("Searching :'" + recipeName + "' (by name) yielded " + recipes.size() + " results"));
        return recipes;
    }

    // EFFECTS: searches for the recipes with the given category name and returns them
    public ArrayList<Recipe> searchCategory(String recipeName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r:myRecipes) {
            if (recipeName.contains(r.getCategory())) {
                recipes.add(r);
            }
        }
        events.logEvent(new Event(
                "Searching :'" + recipeName + "' (by category) yielded " + recipes.size() + " results"));
        return recipes;
    }

    // EFFECTS: returns a random recipe from the recipe book
    public Recipe getRandomRecipe() {
        Random r = new Random();
        Recipe recipe = myRecipes.get(r.nextInt(myRecipes.size()));
        events.logEvent(new Event("Got random recipe: '" + recipe.getName() + "'"));
        return recipe;
    }

    public ArrayList<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public static EventLog getEventLog() {
        return events;
    }

    @Override
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Recipe r : myRecipes) {
            jsonArray.put(r.toJson());
        }

        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Author", author);
        json.put("Recipes", jsonArray);

        return json;
    }
}
