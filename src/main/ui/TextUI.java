package ui;

import model.Instructions;
import model.Recipe;
import model.RecipeBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    private static final Scanner input = new Scanner(System.in);

    public static void mainMenu(RecipeBook rb) {
        while (true) {
            System.out.println("Welcome to the recipe book! Please choose an option below");
            System.out.println("(a)dd recipe                (s)earch recipes");
            System.out.println("(c)ategory search           (d)elete recipes");
            System.out.println("(f)etch random recipe       (e)xit");
            String input = getText().substring(0,1).toLowerCase();
            if (input.equals("a")) {
                addRecipe(rb);
            } else if (input.equals("s")) {
                searchRecipe(rb);
            } else if (input.equals("c")) {
                searchByCategory(rb);
            } else if (input.equals("d")) {
                deleteRecipe(rb);
            } else if (input.equals("f")) {
                getRandomRecipe(rb);
            } else if (input.equals("e")) {
                break;
            } else {
                System.out.println("Sorry, I did not understand");
            }
        }
        System.out.println("Thanks for using the recipe book!");
    }

    public static void addRecipe(RecipeBook rb) {
        while (true) {
            System.out.println("+-----------------+");
            System.out.println("| Add recipe Menu |");
            System.out.println("+-----------------+");
            System.out.println("(a)dd new recipe");
            System.out.println("(e)xit to main menu");
            String input = getText().substring(0,1).toLowerCase();

            if (input.equals("e")) {
                break;
            } else if (!input.equals("a")) {
                System.out.println("Sorry, I did not understand");
                continue;
            }
            createRecipe(rb);
        }
    }

    public static void createRecipe(RecipeBook rb) {
        String name  = askUserForString("What is the name of the recipe?");
        String cat   = askUserForString("What type of recipe is this?");
        int duration = askUserForInt("How long does it take to make?");
        int servings = askUserForInt("How many people does it serve?");
        System.out.println("+---------------------+");
        System.out.println("| Adding Instructions |");
        System.out.println("+---------------------+");
        Instructions ins = new Instructions();
        while (true) {
            String in = askUserForString("Enter instruction or type 'exit' to finish:");
            if (in.startsWith("exit")) {
                break;
            }
            ins.addStep(in);
        }
        Recipe r = new Recipe(name,duration,servings,cat);
        r.setInstructions(ins); //may need to implement this
        rb.addRecipe(r);
    }

    public static void searchRecipe(RecipeBook rb) {
        String name  = askUserForString("What is the name of the recipe?");
        ArrayList<Recipe> recipes = rb.searchRecipe(name);
        System.out.println("-----------------------------------------");
        for (Recipe r : recipes) {
            System.out.println(r);
            System.out.println("-----------------------------------------");
        }
    }

    public static void getRandomRecipe(RecipeBook rb) {
        String name  = askUserForString("Type anything to confirm you want a random recipe");
        Recipe r = rb.getRandomRecipe();
        System.out.println(r);
    }


    public static void searchByCategory(RecipeBook rb) {
        String name = askUserForString("What is the name of the category you want to search for?");
        ArrayList<Recipe> recipes = rb.searchCategory(name);
        System.out.println("-----------------------------------------");
        for (Recipe r : recipes) {
            System.out.println(r);
            System.out.println("-----------------------------------------");
        }
    }

    public static void deleteRecipe(RecipeBook rb) {
        String name  = askUserForString("What is the name of the recipe you want to delete?");
        rb.removeRecipe(name);
    }

    public static String askUserForString(String msg) {
        System.out.print(msg + " ");
        return getText();
    }

    public static int askUserForInt(String msg) {
        System.out.print(msg + " ");
        return getInt();
    }


    public static String getText() {
        String result = input.nextLine();

        return result;
    }

    public static int getInt() {
        try {
            int ans = input.nextInt();
            input.nextLine(); //get rid of the newline cause nextInt doesnt
            return ans;
        } catch (InputMismatchException ex) {
            return 0;
        }
    }
}