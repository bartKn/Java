package pl.bartkn.Algorithm;

import java.util.*;

public class ShuntingYard {

    private final Map<String, Integer> operators;

    public ShuntingYard() {
        operators = new HashMap<>();
        operators.put("+", 0);
        operators.put("-", 0);
        operators.put("*", 1);
        operators.put("/", 1);
    }

    private List<String> stringToList(String input) throws IllegalArgumentException {
        // Check if input is not null
        if (input == null) throw new IllegalArgumentException("Null input");

        // Remove whitespaces
        input = input.replaceAll("\\s+", "");

        // Check if input is not empty after whitespaces removal
        if (input.length() == 0) throw new IllegalArgumentException("Empty input");

        input = input.replaceAll("\\(-", "(0-");

        if (input.startsWith("-")) {
            input = "0" + input;
        }

        input = input.replaceAll("x", "*");

        List<String> charsList = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(input, "[+-*/()]", true);
        while (tokenizer.hasMoreElements()) {
            charsList.add(tokenizer.nextToken());
        }

        return charsList;
    }

    public List<String> inputToRPN(String input) {

        List<String> inputList = stringToList(input);
        List<String> output = new ArrayList<>();
        Stack<String> operatorsStack = new Stack<>();


        for (String token: inputList) {

            if (operators.containsKey(token)) {
                while (!operatorsStack.empty() &&
                        operators.containsKey(operatorsStack.peek()) &&
                        ((operators.get(token) <= operators.get(operatorsStack.peek()) ) || (operators.get(token)) < operators.get(operatorsStack.peek()))
                ) {
                    output.add((operatorsStack.pop()));
                }
                operatorsStack.push(token);
            }

            else if (token.equals("(")) {
                operatorsStack.add(token);
            }
            else if (token.equals(")")) {

                while (!operatorsStack.empty()) {
                    if (!operatorsStack.peek().equals("(")) {
                        output.add(operatorsStack.pop());
                    } else {
                        break;
                    }
                }
                if (!operatorsStack.empty()) {
                    operatorsStack.pop();
                } else {
                    throw new IllegalArgumentException("Missing bracket");
                }
            }
            else {
                output.add(token);
            }
        }

        while (!operatorsStack.empty()) {
            output.add(operatorsStack.pop());
        }

        return output;
    }

    public double calculateRPN(List<String> input) throws IllegalArgumentException, ArithmeticException {

        Stack<Double> result = new Stack<>();

        for (String token: input) {
            if (!operators.containsKey(token)) {
                result.push(Double.parseDouble(token));
            } else {
                if (result.size() > 1) {
                    double op1 = result.pop();
                    double op2 = result.pop();

                    switch (token) {
                        case "+" -> result.push(op2 + op1);
                        case "-" -> result.push(op2 - op1);
                        case "*" -> result.push(op2 * op1);
                        case "/" -> {
                            if (op1 == 0 ) throw new ArithmeticException();
                            result.push(op2 / op1);
                        }
                        default -> throw new IllegalArgumentException("Not supported operator");
                    }
                }
            }
        }
        if (result.empty() || result.size() > 1)
            throw new IllegalArgumentException("Something went wrong");
        return result.peek();
    }
}
