/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-08-08 AM 5:58
 */

// Java Pogram to synchronized method by
// using an anonymous class
import java.io.*;

//  https://www.geeksforgeeks.org/synchronization-in-java/
class Test {
    synchronized void test_sync_function(int n)
    {
        // synchronized method
        for (int i = 1; i <= 3; i++) {
            System.out.println(n + i);
            try {
                Thread.sleep(200);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    void test_function(int n)
    {
        // synchronized method
        for (int i = 1; i <= 3; i++) {
            System.out.println(n + i);
            try {
                Thread.sleep(200);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

// Driver Class
public class GFG {
    // Main function
    public static void main(String args[]) throws InterruptedException {
        // only one object
        final Test obj = new Test();


        Thread a = new Thread(() -> obj.test_sync_function(15));

        Thread b = new Thread(() -> obj.test_sync_function(30));

        System.out.println("-------------------");
        System.out.println("Synchronized Method");
        System.out.println("-------------------");
        a.start();
        b.start();

        a = new Thread(() -> obj.test_function(15));

        b = new Thread(() -> obj.test_function(30));

        Thread.sleep(2000);
        System.out.println("-------------------");
        System.out.println("Non Synchronized Method");
        System.out.println("-------------------");
        a.start();
        b.start();
    }
}