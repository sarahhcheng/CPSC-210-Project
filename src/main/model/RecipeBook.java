package model;

import java.util.ArrayList;
import java.util.Random;

public class RecipeBook {
    private ArrayList<Recipe> myRecipes;
    private String title;
    private String author;

    public RecipeBook(String title, String author) {
        myRecipes = new ArrayList<>();
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfRecipes() {
        return myRecipes.size();
    }

    public void addRecipe(Recipe r) {
        myRecipes.add(r);

    }

    public boolean removeRecipe(Recipe r) {
        return myRecipes.remove(r);
    }

    public boolean removeRecipe(String recipeName) {
        for (Recipe r:myRecipes) {
            if (recipeName.equals(r.getName())) {
                myRecipes.remove(r);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Recipe> searchRecipe(String recipeName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r:myRecipes) {
            if (recipeName.contains(r.getName())) {
                recipes.add(r);
            }
        }
        return recipes;
    }

    public ArrayList<Recipe> searchCategory(String recipeName) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (Recipe r:myRecipes) {
            if (recipeName.contains(r.getCategory())) {
                recipes.add(r);
            }
        }
        return recipes;
    }

    public Recipe getRandomRecipe() {
        Random r = new Random();
        return myRecipes.get(r.nextInt(myRecipes.size()));
    }
}
