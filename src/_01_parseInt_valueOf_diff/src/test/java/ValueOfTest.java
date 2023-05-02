import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValueOfTest {
    @Test
    public void whenNumberIsPassed_thenShouldConvertToInteger() {
        Integer expectedNumber = 11;
        Integer expectedNegativeNumber = -11;
        Integer expectedUnicodeValue = 65;

        assertEquals(expectedNumber, Integer.valueOf(11));
        assertEquals(expectedNumber, Integer.valueOf(+11));
        assertEquals(expectedNegativeNumber, Integer.valueOf(-11));
        assertEquals(expectedUnicodeValue, Integer.valueOf('A'));
    }

    @Test
    public void whenValidNumericStringWithRadixIsPassed_thenShouldConvertToInetger() {
        Integer expectedNumber1 = 17;
        Integer expectedNumber2 = 10;
        Integer expectedNumber3 = 7;

        assertEquals(expectedNumber1, Integer.valueOf("11", 16));
        assertEquals(expectedNumber2, Integer.valueOf("A", 16));
        assertEquals(expectedNumber3, Integer.valueOf("7", 8));
    }
}
