package pl.bartkn.game;

public class Board {

    private int position;
    private String[] board;
    private String lastInserted;

    public Board (int position, String[] board, String lastInserted) {
        this.position = position;
        this.board = board;
        this.lastInserted = lastInserted;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition (int position) {
        this.position = position;
    }

    public String[] getBoard() {
        return board;
    }

    public String getFieldValue(int index) {
        return board[index];
    }

    public void changeField(int index, String playerChar) {
        board[index - 1] = playerChar;
        lastInserted = playerChar;
    }

    public String getLastInserted() {
        return lastInserted;
    }

    public boolean isEmpty() {
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("X") || board[i].equals("O"))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 9; i++) {
            sb.append(board[i - 1]).append(" ");
            if (i == 3 || i == 6)
                sb.append("\n");
        }
        return sb.toString();
    }


}
