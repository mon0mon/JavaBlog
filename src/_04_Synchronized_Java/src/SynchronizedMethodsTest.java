import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedMethodsTest {
    SynchronizedMethods summation;
    ExecutorService service;

    @BeforeEach
    public void initField() {
        summation = new SynchronizedMethods();
        service = Executors.newFixedThreadPool(3);
    }

    @Test
    void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
        IntStream.range(0, 1000)
                .forEach(count -> service.submit(summation::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        //  AssertionFailedError 발생할 것
        //  멀티 쓰레드 간의 Synchronized 자원 접근이 없었기 때문에 발생한 문제
        assertEquals(1000, summation.getSum());
    }

    @Test
    void givenMultiThread_whenMethodSync() throws InterruptedException {
        IntStream.range(0, 1000)
                .forEach(count -> service.submit(summation::synchronisedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        //  Test Pass
        //  자원에 대한 Synchronized 접근 제어가 있었기 때문에, 예상한 결과 도출
        //  Synchronized 키워드는 Blocking 개념
        assertEquals(1000, summation.getSum());
    }

    @Test
    void givenMultiThread_whenStaticSyncMethod() throws InterruptedException {
        IntStream.range(0, 1000)
                .forEach(count -> service.submit(SynchronizedMethods::syncStaticMethod));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        //  static 메소드도 synchronized 키워드는 동일하게 사용 가능
        assertEquals(1000, SynchronizedMethods.getStaticSum());
    }

    @Test
    void givenMultiThread_whenBlockSync() throws InterruptedException {
        IntStream.range(0, 1000)
                .forEach(count -> service.submit(summation::performSynchronisedTask));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, summation.getSum());
    }

    @Test
    void givenMultiThread_whenStaticSyncBlock() throws InterruptedException {
        IntStream.range(0, 1000)
                .forEach(count -> service.submit(SynchronizedMethods::performStaticSyncTask));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizedMethods.getStaticSum());
    }
}