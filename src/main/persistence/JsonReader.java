package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Ingredient;
import model.Recipe;
import model.RecipeBook;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
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

    // EFFECTS: parses workroom from JSON object and returns it
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
            ArrayList<String> instructions = getRecipeInstructions(rb, jsonObject);
            ArrayList<Ingredient> ingredients = getIngredients(rb,jsonObject);

            Recipe r = new Recipe(recipeName, duration, serves, category);
            rb.addRecipe(r);
        }
    }

    private ArrayList<String> getRecipeInstructions(RecipeBook rb, JSONObject jsonObject) {
        JSONArray instructions = jsonObject.getJSONArray("Instructions");
        ArrayList<String> instructionArray = new ArrayList<>();
        for (Object s : instructions) {
            instructionArray.add(s.toString());
        }
        return instructionArray;
    }

    private ArrayList<Ingredient> getIngredients(RecipeBook rb, JSONObject jsonObject) {
        JSONArray instructions = jsonObject.getJSONArray("Instructions");
        ArrayList<Ingredient> ingredientsArray = new ArrayList<>();
        for (Object json : instructions) {
            JSONObject nextIngredient = (JSONObject) json;
            String name = jsonObject.getString("Name");
            double quantity = jsonObject.getDouble("Quantity");
            String units = jsonObject.getString("Units");
            Ingredient i = new Ingredient(name,quantity,units);
            ingredientsArray.add(i);
        }
        return ingredientsArray;
    }
}

