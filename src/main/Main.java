package main;

import classes.Solution;
import exception.MyException;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        try {
            solution.input();
            solution.check();
            solution.calculation();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }
}
