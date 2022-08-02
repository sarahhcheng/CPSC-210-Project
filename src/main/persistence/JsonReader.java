package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Ingredient;
import model.Instructions;
import model.Recipe;
import model.RecipeBook;
import org.json.*;

// Represents a reader that reads the RecipeBook from JSON data stored in file
// Referenced code from the demo example
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the RecipeBook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the RecipeBook from JSON object and returns it
    private RecipeBook parseRecipeBook(JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        RecipeBook rb = new RecipeBook(title, author);
        addRecipes(rb, jsonObject);
        return rb;
    }

    // MODIFIES: rb
    // EFFECTS: parses Recipe from JSON object and adds them to the RecipeBook
    private void addRecipes(RecipeBook rb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;

            String recipeName = nextRecipe.getString("Name");
            int duration = nextRecipe.getInt("Duration Minutes");
            int serves = nextRecipe.getInt("Serves");
            String category = nextRecipe.getString("Category");
            ArrayList<String> instructions = getRecipeInstructions(nextRecipe);
            ArrayList<Ingredient> ingredients = getIngredients(nextRecipe);
            Instructions instruction = new Instructions();
            for (String s: instructions) {
                instruction.addStep(s);
            }

            Recipe r = new Recipe(recipeName, duration, serves, category);

            for (Ingredient i: ingredients) {
                r.addIngredient(i);
            }

            r.setInstructions(instruction);

            rb.addRecipe(r);
        }
    }

    // EFFECTS: grabs the steps of the instructions into a list of instructions
    private ArrayList<String> getRecipeInstructions(JSONObject recipe) {
        JSONArray instructions = recipe.getJSONArray("Instructions");
        ArrayList<String> instructionArray = new ArrayList<>();
        for (Object s : instructions) {
            instructionArray.add((String) s);
        }
        return instructionArray;
    }

    // EFFECTS: grabs the ingredients needed for a recipe into a list
    private ArrayList<Ingredient> getIngredients(JSONObject recipe) {
        JSONArray instructions = recipe.getJSONArray("Ingredients");
        ArrayList<Ingredient> ingredientsArray = new ArrayList<>();
        for (Object json : instructions) {
            JSONObject nextIngredient = (JSONObject) json;
            String name = nextIngredient.getString("Name");
            double quantity = nextIngredient.getDouble("Quantity");
            String units = nextIngredient.getString("Units");
            Ingredient i = new Ingredient(name,quantity,units);
            ingredientsArray.add(i);
        }
        return ingredientsArray;
    }
}

