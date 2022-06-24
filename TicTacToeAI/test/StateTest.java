import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.bartkn.ai.Minimax;
import pl.bartkn.game.Board;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTest {

    public static Collection<Object> boardsProvider() {
        Board board1 = new Board(0, new String[]{"X", "X", "X","O", "O", "6", "O", "O", "X"}, "");
        Board board2 = new Board(0, new String[]{"1", "2", "O","4", "O", "X", "O", "X", "X"}, "");
        Board board3 = new Board(0, new String[]{"X", "X", "O","O", "O", "X", "X", "X", "O"}, "");
        Board board4 = new Board(0, new String[]{"1", "2", "3","O", "5", "6", "7", "8", "X"}, "");

        return Arrays.asList(new Object[][] {
                {board1, "X"},
                {board2, "O"},
                {board3, "Draw"},
                {board4, ""}
        });
    }

    @ParameterizedTest
    @MethodSource("boardsProvider")
    public void parTest(Board board, String result) {
        Minimax bot = new Minimax();
        assertEquals(bot.checkWin(board), result);
    }
}
