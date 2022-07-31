package ui;

import model.Ingredient;
import model.Instructions;
import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        RecipeBook myrecipeBook = new RecipeBook("",""); //to be overwritten
        try {
            JsonReader jr = new JsonReader("./data/My Recipe Book.json");
            myrecipeBook = jr.read();
        } catch (IOException e) {
            createDefaultRecipeBook(myrecipeBook);
        }
        TextUI.mainMenu(myrecipeBook);
    }

    private static void createDefaultRecipeBook(RecipeBook myrecipeBook) {
        myrecipeBook = new RecipeBook("My creation", "Sarah Cheng");
        Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
        Instructions c1 = new Instructions();
        c1.addStep("dncjdfnnjdf");
        c1.addStep("dfjdnfdjf");
        chocolatecookie.setInstructions(c1);
        Ingredient suffering = new Ingredient("suffering", 5, "mL");
        chocolatecookie.addIngredient(suffering);
        myrecipeBook.addRecipe(chocolatecookie);

        Recipe oatmealCookie = new Recipe("oatmeal cookie", 30, 4, "Bakery");
        myrecipeBook.addRecipe(oatmealCookie);
        Recipe noodles = new Recipe("noodles", 20, 1, "Dinner");
        myrecipeBook.addRecipe(noodles);
        Instructions stepone = new Instructions();
        stepone.addStep("This is pain");
        oatmealCookie.setInstructions(stepone);
        Ingredient pain = new Ingredient("pain", 2, "mL");
        oatmealCookie.addIngredient(pain);
    }
}
