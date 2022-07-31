package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Referenced code from the demo example
public class JsonTest {
    protected void checkRecipeBook(RecipeBook rbOriginal, RecipeBook rb) {
        assertEquals(rbOriginal.getTitle(), rb.getTitle());
        assertEquals(rbOriginal.getAuthor(), rb.getAuthor());
        ArrayList<Recipe> allRecipesOriginal = rbOriginal.searchRecipe("");
        ArrayList<Recipe> allRecipes = rb.searchRecipe("");
        checkRecipe(allRecipesOriginal, allRecipes);
    }

    protected void checkRecipe(ArrayList<Recipe> recipes, ArrayList<Recipe> recipeFromBook) {
        assertEquals(recipes.size(), recipeFromBook.size());
        for(int i=0 ;  i < recipes.size() ; i++){
            assertEquals(recipes.get(i).toString(), recipeFromBook.get(i).toString());
        }
    }
}

