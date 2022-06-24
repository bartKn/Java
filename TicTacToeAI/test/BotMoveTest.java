import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.bartkn.ai.Minimax;
import pl.bartkn.game.Board;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotMoveTest {
    public static Collection<Object> boardsProvider() {
        Board board1 = new Board(0, new String[]{"O", "2", "3", "4", "5", "6", "7", "8", "9"}, "O");
        Board board2 = new Board(0, new String[]{"O", "2", "3", "4", "X", "6", "O", "8", "9"}, "O");
        Board board3 = new Board(0, new String[]{"O", "X", "O", "O", "X", "6", "X", "8", "O"}, "O");
        Board board4 = new Board(0, new String[]{"X", "2", "X", "O", "O", "6", "X", "O", "9"}, "0");

        return Arrays.asList(new Object[][] {
                {board1, 5},
                {board2, 4},
                {board3, 8},
                {board4, 2}
        });
    }

    @ParameterizedTest
    @MethodSource("boardsProvider")
    public void parTest(Board board, int move) {
        Minimax bot = new Minimax();
        assertEquals(bot.makeDecision(board), move);
    }
}
