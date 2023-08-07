/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-08-08 AM 6:10
 */

public class Util {
    public static long factorial(int number) {
        long result = 1;
        for (int i = number; i > 0; i--) {
            result *= i;
        }
        return result;
    }
}
