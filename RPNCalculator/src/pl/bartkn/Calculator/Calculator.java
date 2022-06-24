package pl.bartkn.Calculator;

import pl.bartkn.Algorithm.ShuntingYard;

import java.util.List;
import java.util.Scanner;

public class Calculator {

    private String input;
    private List<String> inputInRPN;
    private static ShuntingYard shuntingYard = new ShuntingYard();
    private double result;

    public Calculator() {
        this.input = "";
    }

    public void setInput(String ch) {
        this.input = this.input + ch;
    }

    public void delLastChar() {
        if (this.input.length() > 0)
            this.input = this.input.substring(0, this.input.length() - 1);
    }

    public void delAll() {
        this.input = "";
    }

    public String getInput() {
        return this.input;
    }

    public String getResult() {
        try {
            inputInRPN = shuntingYard.inputToRPN(input);
            result = shuntingYard.calculateRPN(inputInRPN);
            return Double.toString(result);
        } catch (IllegalArgumentException ex) {
            return "Error";
        } catch (ArithmeticException ex) {
            return "Division by 0";
        }

    }



}
