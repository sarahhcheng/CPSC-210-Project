package model;

import java.util.ArrayList;

public class Instructions {
    private ArrayList<String> steps;

    public Instructions() {
        steps = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a step to the current instructions of a recipe
    public void addStep(String s) {
        if (s.trim().length() != 0) {
            steps.add(s);
        }
    }

    // EFFECTS: returns the steps of the instruction
    public ArrayList<String> getStep() {
        return steps;
    }

    // REQUIRES: i is a valid index, 0 <= i < steps.size()
    // EFFECTS: given an index, removes the ccrresponding step from the
    //          ArrayList of instructions
    public void removeStep(int i) {
        steps.remove(i);
    }

    // EFFECTS: returns the number of steps in instructions
    public int getSize() {
        return steps.size();
    }

    // REQUIRES: steps.size >= 0
    // EFFECTS: combines and returns all the instructions into a string
    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < steps.size(); i++) {
            ans += String.format("%d) %s\n", i + 1, steps.get(i));
        }
        return ans;
    }
}
