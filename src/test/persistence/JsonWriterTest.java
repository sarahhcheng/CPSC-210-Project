package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Referenced code from the demo example
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRecipeBook() {
        try {
            RecipeBook rb = new RecipeBook("I don't like you", "Sarah");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeBook.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
            rb = reader.read();
            assertEquals("I don't like you", rb.getTitle());
            assertEquals("Sarah", rb.getAuthor());
            assertEquals(0, rb.getNumberOfRecipes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipeBook() {
        try {
            RecipeBook myrecipeBook = new RecipeBook("My creation", "Sarah Cheng");
            Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
            Instructions c1 = new Instructions();
            c1.addStep("dncjdfnnjdf");
            chocolatecookie.setInstructions(c1);
            Instructions c2 = new Instructions();
            c1.addStep("dfjdnfdjf");
            chocolatecookie.setInstructions(c2);
            myrecipeBook.addRecipe(chocolatecookie);
            Recipe oatmealCookie = new Recipe("oatmeal cookie", 30, 4, "Bakery");
            myrecipeBook.addRecipe(oatmealCookie);
            Recipe noodles = new  Recipe("noodles", 20, 1, "Dinner");
            myrecipeBook.addRecipe(noodles);
            Instructions stepone = new Instructions();
            stepone.addStep("This is pain");
            oatmealCookie.setInstructions(stepone);
            Ingredient pain = new Ingredient("pain", 2, "mL");
            oatmealCookie.addIngredient(pain);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipeBook.json");
            writer.open();
            writer.write(myrecipeBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipeBook.json");
            RecipeBook rb = reader.read();
            checkRecipeBook(myrecipeBook,rb);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
