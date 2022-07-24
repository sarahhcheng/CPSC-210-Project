package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    private static final Scanner input = new Scanner(System.in);

    public String askUserForString(String msg) {
        System.out.print(msg + " ");
        return getText();
    }

    public int askUserForInt(String msg) {
        System.out.print(msg + " ");
        return getInt();
    }

    public double askUserForDouble(String msg) {
        System.out.print(msg + " ");
        return getDouble();
    }

    public String getText() {
        String result = input.nextLine();

        return result;
    }

    public int getInt() {
        try {
            int ans = input.nextInt();
            input.nextLine(); //get rid of the newline cause nextInt doesnt
            return ans;
        } catch (InputMismatchException ex) {
            return 0;
        }
    }

    public double getDouble() {
        try {
            double ans = input.nextDouble();
            input.nextLine(); //get rid of the newline cause nextDouble doesnt
            return ans;
        } catch (InputMismatchException ex) {
            return 0.0;
        }
    }
}