package pl.bartkn.view;

import pl.bartkn.ai.Minimax;
import pl.bartkn.game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameForm {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton resetButton;
    private JCheckBox humanCheckBox;
    private JCheckBox computerCheckBox;
    private JCheckBox player1CheckBox;
    private JCheckBox player2CheckBox;
    private JLabel resultLabel;

    private static GameForm gameForm;
    private Board board;
    private Minimax bot;
    private String move;
    private boolean finished;

    private boolean computerMove;

    public GameForm() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setText(move);
                board.changeField(1, move);
                updateMove();
                button1.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2.setText(move);
                board.changeField(2, move);
                updateMove();
                button2.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3.setText(move);
                board.changeField(3, move);
                updateMove();
                button3.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button4.setText(move);
                board.changeField(4, move);
                updateMove();
                button4.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button5.setText(move);
                board.changeField(5, move);
                updateMove();
                button5.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button6.setText(move);
                board.changeField(6, move);
                updateMove();
                button6.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button7.setText(move);
                board.changeField(7, move);
                updateMove();
                button7.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button8.setText(move);
                board.changeField(8, move);
                updateMove();
                button8.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button9.setText(move);
                board.changeField(9, move);
                updateMove();
                button9.setEnabled(false);
                checkGameStatus(board);
                if (computerMove) {
                    getBotMove();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((player1CheckBox.isSelected() || player2CheckBox.isSelected()) &&
                        (humanCheckBox.isSelected() || computerCheckBox.isSelected())) {
                    prepareGame();
                    button1.setText("  ");
                    button1.setEnabled(true);
                    button2.setText("  ");
                    button2.setEnabled(true);
                    button3.setText("  ");
                    button3.setEnabled(true);
                    button4.setText("  ");
                    button4.setEnabled(true);
                    button5.setText("  ");
                    button5.setEnabled(true);
                    button6.setText("  ");
                    button6.setEnabled(true);
                    button7.setText("  ");
                    button7.setEnabled(true);
                    button8.setText("  ");
                    button8.setEnabled(true);
                    button9.setText("  ");
                    button9.setEnabled(true);

                    resetButton.setEnabled(false);
                    resultLabel.setText("");

                    setCheckBoxes(false);

                    if (computerCheckBox.isSelected() && player2CheckBox.isSelected()) {
                        getBotMove();
                    }
                }
            }
        });

        humanCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    computerCheckBox.setSelected(false);
                    computerMove = false;
                }
            }
        });

        computerCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    humanCheckBox.setSelected(false);
                    computerMove = true;
                }
            }
        });

        player1CheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    player2CheckBox.setSelected(false);
                }
            }
        });

        player2CheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    player1CheckBox.setSelected(false);
                }
            }
        });
    }

    private void prepareGame() {
        if (player1CheckBox.isSelected())
            move = "O";
        else
            move = "X";

        this.board = new Board(0, new String[]{"1", "2", "3","4", "5", "6", "7", "8", "9"}, "");
        finished = false;
    }

    private void getBotMove() {
        if (!finished) {
            int field = bot.makeDecision(board);
            switch (field) {
                case 1 -> {
                    button1.setText(move);
                    button1.setEnabled(false);
                    board.changeField(1, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 2 -> {
                    button2.setText(move);
                    button2.setEnabled(false);
                    board.changeField(2, move);
                    updateMove();
                    checkGameStatus(board);

                }
                case 3 -> {
                    button3.setText(move);
                    button3.setEnabled(false);
                    board.changeField(3, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 4 -> {
                    button4.setText(move);
                    button4.setEnabled(false);
                    board.changeField(4, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 5 -> {
                    button5.setText(move);
                    button5.setEnabled(false);
                    board.changeField(5, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 6 -> {
                    button6.setText(move);
                    button6.setEnabled(false);
                    board.changeField(6, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 7 -> {
                    button7.setText(move);
                    button7.setEnabled(false);
                    board.changeField(7, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 8 -> {
                    button8.setText(move);
                    button8.setEnabled(false);
                    board.changeField(8, move);
                    updateMove();
                    checkGameStatus(board);
                }
                case 9 -> {
                    button9.setText(move);
                    button9.setEnabled(false);
                    board.changeField(9, move);
                    updateMove();
                    checkGameStatus(board);
                }
            }
        }
    }

    private void checkGameStatus(Board board) {
        String result = bot.checkWin(board);
        if (!result.equals("")) {
            if (!result.equals("Draw")) {
                result += " wins";
            }
            finished = true;
            resultLabel.setText(result);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);
            resetButton.setEnabled(true);
            setCheckBoxes(true);
        }
    }

    private void setCheckBoxes(boolean state) {
        computerCheckBox.setEnabled(state);
        humanCheckBox.setEnabled(state);
        player1CheckBox.setEnabled(state);
        player2CheckBox.setEnabled(state);
    }

    private void updateMove() {
        if (move.equals("X"))
            move = "O";
        else
            move = "X";
    }

    public static void init(GameForm gameForm) {
        gameForm.bot = new Minimax();
    }

    public static void main (String[] args) {
        JFrame mainFrame = new JFrame("TicTacToe");
        gameForm = new GameForm();
        init(gameForm);
        mainFrame.setContentPane(gameForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 600);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

}
