package ui;

import com.sun.org.apache.bcel.internal.generic.Instruction;
import model.Instructions;
import model.Recipe;
import model.RecipeBook;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class TextUI {
    private static final Scanner input = new Scanner(System.in);

    public static void mainMenu(RecipeBook rb) {

        while (true) {
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("| Welcome to the recipe book! Please choose an option below |");
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("(a)dd recipe");
            System.out.println("(s)earch recipes");
            System.out.println("(d)elete recipes");
            System.out.println("(e)xit");
            String input = getText().substring(0,1).toLowerCase();

            if (input.equals("a")) {
                addRecipe(rb);
            } else if (input.equals("s")) {
                searchRecipe(rb);
            } else if (input.equals("d")) {
                deleteRecipe(rb);
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

            String name  = askUserForString("What is the name of the recipe?");
            String cat   = askUserForString("What type of recipe is this?");
            int duration = askUserForInt("How long does it take to make?");
            int servings = askUserForInt("How many people does it serve?");
            System.out.println("+---------------------+");
            System.out.println("| Adding Instructions |");
            System.out.println("+---------------------+");
            //Instructions ins = new Instructions();
            ArrayList<String> instructionList = new ArrayList<>();
            while (true) {
                String in = askUserForString("Enter instruction or type 'exit' to finish:");
                if (in.startsWith("exit")) {
                    break;
                }
                instructionList.add(in);
            }
            Recipe r = new Recipe();
            //r.setInstructions(); //may need to implement this
            rb.addRecipe(r);
        }
    }

    public static void searchRecipe(RecipeBook rb) {
        System.out.println("search recipe stub");
    }

    public static void deleteRecipe(RecipeBook rb) {
        System.out.println("delete recipe stub");
    }



    public static String askUserForString(String msg) {
        System.out.print(msg + " ");
        return getText();
    }

    public static int askUserForInt(String msg) {
        System.out.print(msg + " ");
        return getInt();
    }

    public static double askUserForDouble(String msg) {
        System.out.print(msg + " ");
        return getDouble();
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

    public static double getDouble() {
        try {
            double ans = input.nextDouble();
            input.nextLine(); //get rid of the newline cause nextDouble doesnt
            return ans;
        } catch (InputMismatchException ex) {
            return 0.0;
        }
    }
}