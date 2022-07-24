package model;

import sun.rmi.server.InactiveGroupException;

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

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return quantity + " " + units + " " + name;
    }
}
