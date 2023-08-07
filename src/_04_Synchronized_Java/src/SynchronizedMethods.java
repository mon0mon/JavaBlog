/**
 * @project JavaBlog
 * @author ARA
 * @since 2023-08-08 AM 4:20
 */

//  https://www.baeldung.com/java-synchronized
public class SynchronizedMethods {
    private int sum = 0;
    private static int staticSum = 0;

    public void calculate() {
        setSum(getSum() + 1);
    }

    public synchronized void synchronisedCalculate() {
        setSum(getSum() + 1);
    }

    public static synchronized void syncStaticMethod() {
        staticSum++;
    }

    public void performSynchronisedTask() {
        synchronized (this) {
            setSum(getSum() + 1);
        }
    }

    public static void performStaticSyncTask() {
        synchronized (SynchronizedMethods.class) {
            setStaticSum(getStaticSum() + 1);
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public static int getStaticSum() {
        return staticSum;
    }

    public static void setStaticSum(int staticSum) {
        SynchronizedMethods.staticSum = staticSum;
    }
}
