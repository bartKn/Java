package pl.bartkn.ai;

import pl.bartkn.game.Board;

import java.util.ArrayList;
import java.util.Random;

public class Minimax {
    public int makeDecision(Board board){
        ArrayList<Board> possibleMoves = possibleMoves(board);
        ArrayList<Integer> movesList = new ArrayList<>();


        for (Board boards: possibleMoves) {
            movesList.add(minValue(boards));
        }

        int max = movesList.get(0);
        int bestIndex = 0;

        for (int i = 1; i < movesList.size(); i++) {

            if( movesList.get(i) > max){
                max = movesList.get(i);
                bestIndex = i;
            }
        }

        int decision;

        if (board.isEmpty()) {
            int[] array = new int[] {0, 2, 6, 8};
            decision = getRandom(array);
        } else {
            decision = possibleMoves.get(bestIndex).getPosition();
        }

        return decision + 1;
    }

    private int getRandom(int[] array) {
        int rand = new Random().nextInt(array.length);
        return array[rand];
    }

    private int maxValue(Board board){
        if (!checkWin(board).equals("")){
            return winReward(board);
        }

        int value = (int) - Double.POSITIVE_INFINITY;

        for (Board possibleMove: possibleMoves(board)) {
            value = Math.max(value, minValue(possibleMove));
        }
        return value;
    }

    private int minValue(Board board){
        if (!checkWin(board).equals("")){
            return winReward(board);
        }

        int value = (int) Double.POSITIVE_INFINITY;

        for (Board possibleMove: possibleMoves(board)) {
            value = Math.min(value, maxValue(possibleMove));
        }
        return value;
    }

    public String checkWin(Board board) {
        int taken = 0;
        for (int a = 0; a < 9; a++) {
            if(board.getFieldValue(a).equals("X") || board.getFieldValue(a).equals("O") ){
                taken++;
            }

            String line = checkLines(board, a);

            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }

            if(taken == 9){
                return "Draw";
            }
        }
        return "";
    }

    // +1 if X win
    // -1 if O win
    // 0 if draw
    private int winReward(Board board){
        for (int a = 0; a < 8; a++) {
            String line = checkLines(board, a);

            if (line.equals("XXX")) {
                return 1;
            } else if (line.equals("OOO")) {
                return -1;
            }
        }
        return 0;
    }

    private String checkLines(Board board, int a) {
        return switch (a) {
            case 0 -> board.getFieldValue(0) + board.getFieldValue(1) + board.getFieldValue(2);
            case 1 -> board.getFieldValue(3) + board.getFieldValue(4) + board.getFieldValue(5);
            case 2 -> board.getFieldValue(6) + board.getFieldValue(7) + board.getFieldValue(8);
            case 3 -> board.getFieldValue(0) + board.getFieldValue(3) + board.getFieldValue(6);
            case 4 -> board.getFieldValue(1) + board.getFieldValue(4) + board.getFieldValue(7);
            case 5 -> board.getFieldValue(2) + board.getFieldValue(5) + board.getFieldValue(8);
            case 6 -> board.getFieldValue(0) + board.getFieldValue(4) + board.getFieldValue(8);
            case 7 -> board.getFieldValue(2) + board.getFieldValue(4) + board.getFieldValue(6);
            default -> "";
        };
    }

    private ArrayList<Board> possibleMoves(Board board){
        ArrayList<Board> possibleMoves = new ArrayList<>();
        
        String nextMove = switch (board.getLastInserted()) {
            case "O" -> "X";
            case "X" -> "O";
            case "" -> "X";
            default -> "X";
        };

        for (int i = 0; i < 9; i++) {
            String[] boardCopy = board.getBoard().clone();

            if(!boardCopy[i].equals("X") && !boardCopy[i].equals("O")){
                boardCopy[i] = nextMove;
                possibleMoves.add(new Board(i, boardCopy, nextMove));
            }
        }
        return possibleMoves;
    }
}
