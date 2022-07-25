package ui;

import model.Ingredient;
import model.Instructions;
import model.Recipe;
import model.RecipeBook;

public class Main {
    public static void main(String[] args) {
        RecipeBook myrecipeBook = new RecipeBook("My creation", "Sarah Cheng");
        Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
        myrecipeBook.addRecipe(chocolatecookie);
        Recipe oatmealCookie = new Recipe("outmeal cookie", 30, 4, "Bakery");
        myrecipeBook.addRecipe(oatmealCookie);
        Recipe noodles = new  Recipe("noodles", 20, 1, "Dinner");
        myrecipeBook.addRecipe(noodles);
        Instructions stepone = new Instructions();
        stepone.addStep("This is pain");
        oatmealCookie.setInstructions(stepone);
        Ingredient pain = new Ingredient("pain", 2, "mL");
        oatmealCookie.addIngredient(pain);



        TextUI.mainMenu(myrecipeBook);
    }
}
