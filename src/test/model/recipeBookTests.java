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
        Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
        myrecipeBook.addRecipe(chocolatecookie);
        Recipe oatmealCookie = new Recipe("outmeal cookie", 30, 4, "Bakery");
        myrecipeBook.addRecipe(oatmealCookie);
        Recipe noodles = new  Recipe("noodles", 20, 1, "Dinner");
        myrecipeBook.addRecipe(noodles);
        Recipe sushi = new Recipe("sushi", 30, 2, "Dinner");
        assertEquals(myrecipeBook.searchRecipe("cookie").size(), 2);
        assertEquals(myrecipeBook.searchRecipe("noodles").size(), 1);

    }

    @Test
    public void testSearchByCategory() {
        assertEquals(myrecipeBook.searchCategory("Bakery").size(), 0);
        assertEquals(myrecipeBook.searchCategory("Dinner").size(), 0);
        Recipe chocolatecookie = new Recipe("chocolate cookie", 20, 5, "Bakery");
        myrecipeBook.addRecipe(chocolatecookie);
        Recipe oatmealCookie = new Recipe("oatmeal cookie", 30, 4, "Bakery");
        myrecipeBook.addRecipe(oatmealCookie);
        Recipe noodles = new  Recipe("noodles", 20, 1, "Dinner");
        myrecipeBook.addRecipe(noodles);
        Recipe sushi = new Recipe("sushi", 30, 2, "Dinner");
        myrecipeBook.addRecipe(noodles);
        assertEquals(myrecipeBook.searchCategory("Bakery").size(), 2);
        assertEquals(myrecipeBook.searchCategory("Dinner").size(), 2);

    }

    @Test
    public void testGetRandomRecipe() {
        Recipe applePie = new Recipe();
        Recipe blueberryMuffin = new Recipe("blueberryMuffin", 20, 5, "Bakery");
        myrecipeBook.addRecipe(applePie);
        assertEquals(applePie, myrecipeBook.getRandomRecipe());
        myrecipeBook.addRecipe(blueberryMuffin);
        assertTrue(myrecipeBook.getRandomRecipe() != null);
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
    public void testInstructionToString() {
        Instructions howTo = new Instructions();
        howTo.addStep("Preheat oven to 180 degrees");
        assertEquals("1) Preheat oven to 180 degrees\n", howTo.toString());
        howTo.addStep("Add one cup of flour into bowl");
        assertEquals("1) Preheat oven to 180 degrees\n2) Add one cup of flour into bowl\n", howTo.toString());
    }

    @Test
    public void testIngredientsToString() {
        Ingredient flour = new Ingredient("flour", 20, "g");
        assertEquals("20.0 g flour", flour.toString());
    }

    @Test
    public void testRecipeToString() {
        Ingredient flour = new Ingredient("flour", 20, "g");
        Ingredient sugar = new Ingredient("sugar", 30, "g");
        assertEquals("20.0 g flour", flour.toString());
        Instructions howTo = new Instructions();
        howTo.addStep("Preheat oven to 180 degrees");
        howTo.addStep("Add one cup of flour into bowl");
        Recipe Baking = new Recipe();
        Baking.setInstructions(howTo);
        Baking.addIngredient(flour);
        Baking.addIngredient(sugar);
        assertEquals(": Serves 0. Takes 0 minutes to cook\n20.0 g flour\n30.0 g sugar\n\n1) Preheat oven to 180 degrees" +
                "\n2) Add one cup of flour into bowl\n", Baking.toString());
    }
}