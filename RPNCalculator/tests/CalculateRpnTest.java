import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.bartkn.Algorithm.ShuntingYard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateRpnTest {

    public static Collection<Object[]> rpnProvider() {
        return Arrays.asList(new Object[][]{
                {"2 3 + 5 *", 25.0},
                {"1 2 + 4 * 5 + 3 -", 14.0},
                {"3 4 2 1 - * +", 7.0},
                {"2 7 + 3 / 14 3 - 4 * + 2 /", 23.5}
        });
    }

    @ParameterizedTest
    @MethodSource("rpnProvider")
    public void parTest(String input, double result){
        ShuntingYard shuntingYard = new ShuntingYard();
        List<String> inputList = new ArrayList<>(Arrays.asList(input.split(" ")));
        assertEquals(result, shuntingYard.calculateRPN(inputList));
    }
}
