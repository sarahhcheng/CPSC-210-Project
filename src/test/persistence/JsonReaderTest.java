package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    // Referenced code from the demo example
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals("My creation", rb.getTitle());
            assertEquals("Sarah Cheng", rb.getAuthor());

            Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
            Instructions c1 = new Instructions();
            c1.addStep("dncjdfnnjdf");
            c1.addStep("dfjdnfdjf");
            chocolatecookie.setInstructions(c1);
            Ingredient suffering = new Ingredient("suffering", 5, "mL");
            chocolatecookie.addIngredient(suffering);
            ArrayList<Recipe> recipeWrapper = new ArrayList<>();
            recipeWrapper.add(chocolatecookie);

            ArrayList<Recipe> savedRecipes = rb.searchRecipe("chocolate cookie");
            assertEquals(savedRecipes.size(),1);
            checkRecipe(recipeWrapper, savedRecipes);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
