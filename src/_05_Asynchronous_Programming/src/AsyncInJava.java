import com.google.common.util.concurrent.*;
import com.jcabi.aspects.Async;
import com.jcabi.aspects.Loggable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-08-08 AM 6:09
 */

//  https://www.baeldung.com/java-asynchronous-programming
public class AsyncInJava {

    private int number;
    private long res;
    private final Long expected = 2432902008176640000L;

    @BeforeEach
    public void initNumber() {
        number = 20;
    }

    @Test
    public void thread() {
        Thread newThread = new Thread(() -> {
            System.out.println("Factorial of " + number + " is : " + Util.factorial(number));
        });
        newThread.start();
    }

    @Test
    public void futureTask() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Long> futureTask = threadPool.submit(() -> res = Util.factorial(number));

        while (!futureTask.isDone()) {
            System.out.println("FutureTask is not finished yet...");
        }
        long result = futureTask.get();
        System.out.println("Factorial of " + number + " is : " + res);

        threadPool.shutdown();
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void completableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> Util.factorial(number));
        while (!completableFuture.isDone()) {
            System.out.println("CompletableFuture is not finished yet..");
        }
        res = completableFuture.get();
        System.out.println("Factorial of " + number + " is : " + res);

        Assertions.assertEquals(expected, res);
    }

    @Test
    public void guava() throws ExecutionException, InterruptedException {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);
        ListenableFuture<Long> guavaFuture = service.submit(() -> Util.factorial(number));
        res = guavaFuture.get();

        System.out.println("Factorial of " + number + " is : " + res);
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void guava2() throws ExecutionException, InterruptedException {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);
//        AsyncCallable<Long> asyncCallable = Callables.asAsyncCallable(new Callable<Long>() {
//            @Override
//            public Long call() throws Exception {
//                return Util.factorial(number);
//            }
//        }, service);
        AsyncCallable<Long> asyncCallable = Callables.asAsyncCallable(() -> Util.factorial(number), service);
        ListenableFuture<Long> guavaFuture = Futures.submitAsync(asyncCallable, service);
        res = guavaFuture.get();

        System.out.println("Factorial of " + number + " is : " + res);
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void guava3() {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);
        AsyncCallable<Long> asyncCallable = Callables.asAsyncCallable(() -> Util.factorial(number), service);
        ListenableFuture<Long> guavaFuture = Futures.submitAsync(asyncCallable, service);
        Futures.addCallback(
                guavaFuture,
                new FutureCallback<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        res = aLong;
                        System.out.println("Factorial of " + number + " is : " + res);
                        Assertions.assertEquals(expected, res);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.getCause();
                    }
                },
                service
        );
    }

    @Test
    public void eaASync1() throws ExecutionException, InterruptedException {
        //  경고가 나오는데 해결을 못하겠음...
        com.ea.async.Async.init();
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> Util.factorial(number));
        res = com.ea.async.Async.await(completableFuture);

        System.out.println("Factorial of " + number + " is : " + res);
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void cactoos() throws ExecutionException, InterruptedException {
        org.cactoos.func.Async<Integer, Long> asyncFunction =
                new org.cactoos.func.Async<Integer, Long>(input -> Util.factorial(input));
        Future<Long> asyncFuture = asyncFunction.apply(number);
        res = asyncFuture.get();

        System.out.println("Factorial of " + number + " is : " + res);
        Assertions.assertEquals(expected, res);
    }
}
