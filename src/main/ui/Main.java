package ui;

import model.Ingredient;
import model.Instructions;
import model.Recipe;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Main {
    public static void main(String[] args) {
        TextUI t = new TextUI();
//        Ingredient i1 = new Ingredient("Food",3,"kg");
//        Ingredient i2 = new Ingredient("Drinks",2,"L");
//        Instructions ins = new Instructions();
//
//        Recipe r = new Recipe("Dinner",3,1,"Good stuff");
//        r.getInstructions().addStep("Set the table"); // you should make this easier
//        r.getInstructions().addStep("Place the food on the table");
//        r.getInstructions().addStep("Eat the food");
//        r.addIngredient(i1);
//        r.addIngredient(i2);
//
//        System.out.println("Print Ingredients");
//        System.out.println(i1);
//        System.out.println(i2);
//
//        System.out.println("\nGet Instructions");
//
//        System.out.println(r.getInstructions());
//
//        System.out.println("\nPrint recipe");
//        System.out.println(r);

        String recipeName = t.askUserForString("What is the name of your recipe?");
        double recipeDuration = t.askUserForDouble("How many minutes will your recipe take?");
        int recipeUnits = t.askUserForInt("How many grams of your ingredient will you need?");

        System.out.println("Recipe: " + recipeName + "will take " + recipeDuration + " minutes to make");


    }
}
