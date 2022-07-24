package model;

import java.awt.*;
import java.util.ArrayList;

public class Recipe {

    private String name;
    private Image img; //stub
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

    public String getName() {
        return name;
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public ArrayList getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String ans = "";
        //add ingredients in a for-loop
        for (int i = 0; i < ingredients.size(); i++) {
            ans += ingredients.get(i) + "\n";
        }
        ans += "\n" + instructions.toString();

        return ans;
    }
}
