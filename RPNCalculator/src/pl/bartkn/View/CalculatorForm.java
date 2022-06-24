package pl.bartkn.View;

import pl.bartkn.Algorithm.ShuntingYard;
import pl.bartkn.Calculator.Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorForm {
    private JPanel mainPanel;
    private JButton a0Button;
    private JButton a1Button;
    private JButton a4Button;
    private JButton a7Button;
    private JButton delButton;
    private JButton dotButton;
    private JButton a2Button;
    private JButton a5Button;
    private JButton a8Button;
    private JButton resButton;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a9Button;
    private JButton exitButton;
    private JButton clrButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton divButton;
    private JButton mltpButton;
    private JButton subButton;
    private JButton addButton;
    private JTextField TextField;

    private Calculator calculator;

    private static CalculatorForm calculatorForm;


    public CalculatorForm() {
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("0");
                printTextField();
            }
        });

        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("1");
                printTextField();
            }
        });

        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("2");
                printTextField();
            }
        });

        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("3");
                printTextField();
            }
        });

        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("4");
                printTextField();
            }
        });

        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("5");
                printTextField();
            }
        });

        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("6");
                printTextField();
            }
        });

        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("7");
                printTextField();
            }
        });

        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("8");
                printTextField();
            }
        });

        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("9");
                printTextField();
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("(");
                printTextField();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput(")");
                printTextField();
            }
        });

        divButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("/");
                printTextField();
            }
        });

        mltpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("x");
                printTextField();
            }
        });

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("-");
                printTextField();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput("+");
                printTextField();
            }
        });

        dotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.setInput(".");
                printTextField();
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.delLastChar();
                printTextField();
            }
        });

        clrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculator.delAll();
                printTextField();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTextField(calculator.getResult());
                calculator.delAll();
            }
        });


    }

    public void printTextField() {
        TextField.setText(calculator.getInput());
    }

    public void printTextField(String text) {
        TextField.setText(text);
    }

    public static void init(CalculatorForm calculatorForm) {
        calculatorForm.calculator = new Calculator();
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Kalkulator");
        calculatorForm = new CalculatorForm();
        init(calculatorForm);
        mainFrame.setContentPane(calculatorForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 600);
        mainFrame.setResizable(false);
        //mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
