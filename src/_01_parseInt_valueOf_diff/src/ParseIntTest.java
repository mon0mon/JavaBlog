import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ParseIntTest {
    @Test
    public void whenValidNumericStringIsPassed_thenShouldConvertToPrimitiveInt() {
        Assertions.assertEquals(11, Integer.parseInt("11"));
        Assertions.assertEquals(11, Integer.parseInt("+11"));
        Assertions.assertEquals(11, Integer.parseInt("-11"));

        //  expected: <11> but was: <-11>
        //  Expected :11
        //  Actual   :-11
    }

    @Test
    public void whenValidNumericStringWithRadixIsPassed_thenShouldConvertToPrimitiveInt() {
        Assertions.assertEquals(17, Integer.parseInt("11", 16));
        Assertions.assertEquals(10, Integer.parseInt("A", 16));
        Assertions.assertEquals(7, Integer.parseInt("7", 8));
    }

    @Test
    public void whenValidNumericStringWithRadixAndSubstringIsPassed_thenShouldConvertToPrimitiveInt() {
        Assertions.assertEquals(5, Integer.parseInt("100101", 3, 6, 2));
        Assertions.assertEquals(101, Integer.parseInt("100101", 3, 6, 10));
    }

    @Test
    public void whenInValidNumericStringIsPassed_thenShouldThrowNumberFormatException(){
        Assertions.assertThrows(NumberFormatException.class, () -> {
            int number = Integer.parseInt("abcd");
        });
    }
}
