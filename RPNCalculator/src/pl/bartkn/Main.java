package pl.bartkn;

import pl.bartkn.Algorithm.ShuntingYard;
import pl.bartkn.Calculator.Calculator;

import javax.swing.*;
import java.util.List;

public class Main {

    static ShuntingYard shuntingYard = new ShuntingYard();

    public static void main(String[] args) {


        try {
            List<String> result = shuntingYard.inputToRPN("((2+7)/3+(14-3)*4)/2");
            double calculated = shuntingYard.calculateRPN(result);
            System.out.println(calculated);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }





    }
}
