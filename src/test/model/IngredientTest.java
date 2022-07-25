package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {
    private Ingredient newIngredient;

    @BeforeEach
    public void setup() {
        newIngredient = new Ingredient("flour", 20, "mL");
    }

    @Test
    public void testgetName() {
        assertEquals("flour", newIngredient.getName());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(20.0, newIngredient.getQuantity());
    }

    @Test
    public void testGetUnits() {
        assertEquals("mL", newIngredient.getUnits());
    }

    @Test
    public void testIngredientToString() {
        assertEquals("20.0 mL flour", newIngredient.toString());
    }
}
