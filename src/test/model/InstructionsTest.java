package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InstructionsTest {
    private Instructions newInstruction;

    @BeforeEach
    public void setup() {
        newInstruction = new Instructions();
    }

    @Test
    public void addStepTest() {
        newInstruction.addStep("First, add a cup of flour");
        newInstruction.addStep("");
        assertEquals(newInstruction.getSize(), 1);
    }

    @Test
    public void testGetStep() {
        newInstruction.addStep("First, add a cup of flour");
        assertTrue(newInstruction.getStep().size() != 0);
    }

    @Test
    public void testGetSize() {
        assertEquals(newInstruction.getSize(), 0);
        newInstruction.addStep("First, add a cup of flour");
        assertEquals(newInstruction.getSize(), 1);
    }

    @Test
    public void testInstructionToString() {
        newInstruction.addStep("First, add a cup of flour");
        newInstruction.addStep("Then add another cup of sugar");
        newInstruction.addStep("Mix well");
        assertEquals("1) First, add a cup of flour" +
                "\n2) Then add another cup of sugar\n3) Mix well\n", newInstruction.toString());
    }

    @Test
    public void testRemoveStep() {
        newInstruction.addStep("First, add a cup of flour");
        newInstruction.addStep("Then add another cup of sugar");
        newInstruction.removeStep(0);
        assertEquals("1) Then add another cup of sugar\n", newInstruction.toString());
    }

}
