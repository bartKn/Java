import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.bartkn.Algorithm.ShuntingYard;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShuntingYardTest {

    public static Collection<String[]> shuntingYardProvider() {
        return Arrays.asList(new String[][]{
                {"(2+3)*5", "2 3 + 5 *"},
                {"(1+2)   * 4 + 5 - 3", "1 2 + 4 * 5 + 3 -"},
                {"3+4*(2-1)", "3 4 2 1 - * +"},
                {"((2+7)/3+(14-3)*4)/2", "2 7 + 3 / 14 3 - 4 * + 2 /"},
                {"(1+2) * 4 + 5 - 3", "1 2 + 4 * 5 + 3 -"}
        });
    }

    @ParameterizedTest
    @MethodSource("shuntingYardProvider")
    public void parTest(String input, String rpnOutput) {
        ShuntingYard shuntingYard = new ShuntingYard();
        assertEquals(rpnOutput, String.join(" ", shuntingYard.inputToRPN(input)));
    }

    @Test
    public void illegalInput() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ShuntingYard shuntingYard = new ShuntingYard();
                //assertEquals("Output", String.join(" ", shuntingYard.inputToRPN("")));
                assertEquals("Output", String.join(" ", shuntingYard.inputToRPN("5+3)")));
            }
        });
    }
}


