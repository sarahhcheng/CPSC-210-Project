package model;

import java.util.ArrayList;

public class Instructions {
    private ArrayList<String> steps;

    public Instructions() {
        steps = new ArrayList<>();
    }

    public void addStep(String s) {
        steps.add(s);
    }

    // REQUIRES: i is a valid index, 0 <= i < steps.size()
    public void removeStep(int i) {
        steps.remove(i);
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < steps.size(); i++) {
            ans += String.format("%d) %s\n", i + 1, steps.get(i));
        }
        return ans;
    }
}
