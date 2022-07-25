package model;

public class Ingredient {
    private String name;
    private double quantity;
    private String units;

    public Ingredient(String name, double quantity, String units) {
        this.name = name;
        this.quantity = quantity;
        this.units = units;
    }

    public Ingredient() {
        this.name = "";
        this.quantity = 0;
        this.units = "";
    }

    // EFFECTS: returns the name of the ingredient
    public String getName() {
        return name;
    }

    // EFFECTS: returns the amount needed of an ingredient
    public double getQuantity() {
        return quantity;
    }

    // EFFECTS: returns the unit used to measure the ingredient
    public String getUnits() {
        return units;
    }

    // EFFECTS: combines the ingredient name, quantity and units into a sentence
    @Override
    public String toString() {
        return quantity + " " + units + " " + name;
    }
}
