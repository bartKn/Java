package pl.bartkn;

import pl.bartkn.ai.Minimax;
import pl.bartkn.game.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Minimax bot = new Minimax();

        Board board = new Board(0,  new String[]{"1", "2", "3","4", "5", "6", "7", "8", "9"}, "");

        int botDecision;
        int playerDecision;

        Scanner scanner = new Scanner(System.in);

        /*System.out.println(board);
        System.out.print(": ");

        playerDecision = Integer.parseInt(scanner.nextLine());
        board.changeField(playerDecision, "O");*/

        while (bot.checkWin(board).equals("")) {
            botDecision = bot.makeDecision(board);
            board.changeField(botDecision, "X");
            if (bot.checkWin(board).equals("")) {
                System.out.println(board);
                System.out.println(": ");
                playerDecision = Integer.parseInt(scanner.nextLine());
                board.changeField(playerDecision, "O");
            }
        }
        System.out.println(board);
        System.out.println(bot.checkWin(board));
    }
}
