package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class recipeBookTests {
    private RecipeBook myrecipeBook;
    private RecipeBook newRecipleBook;

    @BeforeEach
    public void setup() {
        myrecipeBook = new RecipeBook("My creation", "Sarah Cheng" );
        newRecipleBook = new RecipeBook("someone's creations", "Unknown");
    }

    @Test
    public void testEmptyRecipeBook() {
        assertEquals(myrecipeBook.getNumberOfRecipes(), 0);
        assertEquals(newRecipleBook.getNumberOfRecipes(), 0);
    }

    @Test
    public void testGetAuthor() {
        assertEquals(myrecipeBook.getAuthor(), "Sarah Cheng");
        assertEquals(newRecipleBook.getAuthor(), "Unknown");
    }

    @Test
    public void testTitle() {
        assertEquals(myrecipeBook.getTitle(), "My creation");
        assertEquals(newRecipleBook.getTitle(), "someone's creations");
    }

    @Test
    public void addRecipeTest() {
        Recipe applePie = new Recipe();
        assertEquals(0, myrecipeBook.getNumberOfRecipes());
        myrecipeBook.addRecipe(applePie);
        assertEquals(1, myrecipeBook.getNumberOfRecipes());
        // might need to add new tests later
    }

    @Test
    public void testRemoveRecipe() {
        Recipe applePie = new Recipe();
        assertFalse(myrecipeBook.removeRecipe(applePie));
        myrecipeBook.addRecipe(applePie);
        assertTrue(myrecipeBook.removeRecipe(applePie));
    }

    @Test
    public void testRemoveRecipeByName() {
        Recipe blueberryMuffin = new Recipe("blueberryMuffin", 20, 5, "Bakery");
        assertFalse(myrecipeBook.removeRecipe("blueberryMuffin"));
        myrecipeBook.addRecipe(blueberryMuffin);
        assertEquals(1, myrecipeBook.getNumberOfRecipes());
        assertTrue(myrecipeBook.removeRecipe("blueberryMuffin"));
    }

    @Test
    public void testSearchRecipe() {
        // Need to finish this test
    }

    @Test
    public void testGetRandomRecipe() {
        Recipe applePie = new Recipe();
        Recipe blueberryMuffin = new Recipe("blueberryMuffin", 20, 5, "Bakery");
        myrecipeBook.addRecipe(applePie);
        myrecipeBook.addRecipe(blueberryMuffin);
        // not sure how to test this??
    }

    @Test
    public void testAddSteps() {
       Instructions howTo = new Instructions();
       assertEquals(0, howTo.getStep().size());
       howTo.addStep("Preheat oven to 180 degrees");
       assertEquals(1, howTo.getStep().size());
    }

    @Test
    public void testRemoveSteps() {
        Instructions howTo = new Instructions();
        assertEquals(0, howTo.getStep().size());
        howTo.addStep("Preheat oven to 180 degrees");
        howTo.addStep("Add one cup of flour into bowl");
        assertEquals(2, howTo.getStep().size());
        howTo.removeStep(1);
        assertEquals(1, howTo.getStep().size());
    }

    @Test
    public void testIngredientToString() {
        Instructions howTo = new Instructions();
        howTo.addStep("Preheat oven to 180 degrees");
        assertEquals("1) Preheat oven to 180 degrees", howTo.toString());

    }




}