package model;

import java.awt.*;
import java.util.ArrayList;

public class Recipe {

    private String name;
//    private Image img; //stub
    private Instructions instructions;
    private ArrayList<Ingredient> ingredients;
    private int durationMinutes;
    private int serves;
    private String category;


    public Recipe() {
        this.name = "";
        this.instructions = new Instructions();
        this.ingredients = new ArrayList<>();
        this.durationMinutes = 0;
        this.serves = 0;
        this.category = "";
    }

    public Recipe(String name, int durationMinutes, int serves, String category) {
        this.name = name;
        this.instructions = new Instructions();
        this.ingredients = new ArrayList<>();
        this.durationMinutes = durationMinutes;
        this.serves = serves;
        this.category = category;

    }

    // EFFECTS: returns the name of the recipe
    public String getName() {
        return name;
    }

    // EFFECTS: gets the instructions needed to create the recipe
    public Instructions getInstructions() {
        return instructions;
    }

    // EFFECTS: sets the written instruction into the ArrayList of instructions
    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    // EFFECTS: gets the ingredients required to create the recipe
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    // MODIFIES: Ingredients and this
    // EFFECTS: adds a new ingredient into the current ArrayList of ingredients
    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }

    // EFFECTS: gets the amount of minutes needed to create the recipe
    public int getDurationMinutes() {
        return durationMinutes;
    }

    // REQUIRES: the duration time has to be greater than 0
    // MODIFIES: this
    // EFFECTS: sets the amount of minutes needed to create the recipe
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    // EFFECTS: gets the amount of people that the recipe can serve
    public int getServes() {
        return serves;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: sets the amount of people that the recipe can serve
    public void setServes(int serves) {
        this.serves = serves;
    }

    // EFFECTS: returns the category of the ingredient
    public String getCategory() {
        return category;
    }

    // MODIFIES: this
    // EFFECTS: sets the category of the type of recipe
    public void setCategory(String category) {
        this.category = category;
    }

    // REQUIRES: ingredients.size >= 0
    // EFFECTS: combines and returns the written ingredients and the instructions
    @Override
    public String toString() {
        String ans = String.format("%s: Serves %d. Takes %d minutes to cook\n",name,serves,durationMinutes);
        //add ingredients in a for-loop
        for (int i = 0; i < ingredients.size(); i++) {
            ans += ingredients.get(i) + "\n";
        }
        if (instructions.toString().length() > 2) {
            ans += "\n" + instructions.toString();
        }

        return ans;
    }
}
