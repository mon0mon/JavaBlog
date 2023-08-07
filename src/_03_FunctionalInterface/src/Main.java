import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-06-27 : PM 20:31
 */

public class Main {

    public static void main(String[] args) {
        //  Stream.forEach
        int nonAtomicCounter = 0;

        //  Compile Error
        //  Variable used in lambda expression should be final or effectively final
//        IntStream.rangeClosed(0, 10).forEach(i -> nonAtomicCounter++);

        AtomicInteger atomicCounter = new AtomicInteger(0);

        IntStream.rangeClosed(0, 10).forEach(i -> atomicCounter.addAndGet(1));

        //  Collections.forEach
        List<Integer> list = IntStream.of(0, 10).boxed().toList();

        int collectionNonAtomicCounter = 0;

        //  Compile Error
        //  Variable used in lambda expression should be final or effectively final
//        list.forEach(i -> collectionNonAtomicCounter++);

        AtomicInteger collectionAtomicCounter = new AtomicInteger(0);

        list.forEach(i -> collectionAtomicCounter.addAndGet(1));
    }
}
