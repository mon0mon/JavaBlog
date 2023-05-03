import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class valueOfToStringTest {
    @Test
    void valueOfToStringDiff() {
        String str = null;

        //  "null"을 리턴
        System.out.println(String.valueOf(str));
        //  NullPointerException을 발생시킴
        Assertions.assertThrows(NullPointerException.class, () -> {
            System.out.println(str.toString());
        });
    }
}
