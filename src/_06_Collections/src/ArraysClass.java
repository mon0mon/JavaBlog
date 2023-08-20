/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-08-20 AM 9:26
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraysClass {
    private final Logger log = LoggerFactory.getLogger(ArraysClass.class);
    private final int[] val = new int[]{10, 8, 12, 2, 0, 5};

    @Test
    public void partialSort() {
        Arrays.sort(val, 0, 4);

        log.info(Arrays.toString(val));
    }

    @Test
    public void binarySearch() {
        //  반드시 Collections.sort(T[])를 해야함
        //  중복된 요소가 있다면, 어떤 걸 반환할 지는 undefined
        Arrays.sort(val);

        Assertions.assertEquals(0, Arrays.binarySearch(val, 0));
        Assertions.assertEquals(1, Arrays.binarySearch(val, 2));
        Assertions.assertTrue(0 > Arrays.binarySearch(val, 4));
    }

    @Test
    public void mismatch() {
        int[] ary1 = new int[]{1, 2, 3, 4};
        int[] ary2 = new int[]{1, 2, 3, 4};

        //  [info] -1
        log.info(String.valueOf(Arrays.mismatch(ary1, ary2)));
        Assertions.assertEquals(-1, Arrays.mismatch(ary1, ary2));

        ary2 = new int[]{1, 2, 3, 5};

        //  [info] 4
        log.info(String.valueOf(Arrays.mismatch(ary1, ary2)));
        Assertions.assertEquals(4, Arrays.mismatch(ary1, ary2));

        ary2 = new int[]{1, 2, 4, 5};

        //  [info] 2
        log.info(String.valueOf(Arrays.mismatch(ary1, ary2)));
        Assertions.assertEquals(2, Arrays.mismatch(ary1, ary2));

        ary2 = new int[]{2, 3, 4, 5};

        //  [info] 0
        log.info(String.valueOf(Arrays.mismatch(ary1, ary2)));
        Assertions.assertEquals(0, Arrays.mismatch(ary1, ary2));
    }

    @Test
    public void indexSubList() {
        List<Integer> parentList = List.of(1, 2, 3, 4, 5);
        List<Integer> childList = List.of(2, 3, 4);

        Assertions.assertEquals(1, Collections.indexOfSubList(parentList, childList));

        childList = List.of(1, 3, 4);

        Assertions.assertEquals(-1, Collections.indexOfSubList(parentList, childList));
    }

    @Test
    public void disjoint() {
        //  하나라도 다른 요소가 있을 경우 true
        //  그외에는 false
        List<Integer> c1 = List.of(1, 2, 3, 4);
        Set<Integer> c2 = new HashSet<>(List.of(5, 6, 7, 8));
        Assertions.assertTrue(Collections.disjoint(c1, c2));

        c2 = new HashSet<>(List.of(1, 5, 6, 7, 8));
        Assertions.assertFalse(Collections.disjoint(c1, c2));
    }

    @Test
    public void fill() {
        List<Integer> list = new ArrayList<>(5);

        Collections.fill(list, 1);

        Assertions.assertTrue(list.stream().allMatch(i -> i == 1));
    }

    @Test
    public void frequency() {
        List<Integer> list = List.of(1, 2, 3, 1, 2, 1);

        Assertions.assertEquals(3, Collections.frequency(list, 1));
        Assertions.assertEquals(2, Collections.frequency(list, 2));
        Assertions.assertEquals(1, Collections.frequency(list, 3));
    }

    @Test
    public void rotate() {
        //  distance는 인덱스 0을 기준으로 더해가는 방식
        //  음수의 경우 뒤에서부터 시작
        List<Integer> list = new ArrayList<>(IntStream.rangeClosed(0, 5).boxed().toList());

        Collections.rotate(list, -1);

//        [info] [1, 2, 3, 4, 5, 0]
        log.info(list.toString());

        Collections.rotate(list, -1);

//        [info] [2, 3, 4, 5, 0, 1]
        log.info(list.toString());

        Collections.rotate(list, 4);


//        [info] [4, 5, 0, 1, 2, 3]
        log.info(list.toString());
    }

    @Test
    public void shuffle() {
        List<Integer> list = new ArrayList<>(IntStream.rangeClosed(1, 5).boxed().toList());

        Collections.shuffle(list);

        log.info(list.toString());

        Collections.shuffle(list, new Random(10));

        log.info(list.toString());
    }
}
