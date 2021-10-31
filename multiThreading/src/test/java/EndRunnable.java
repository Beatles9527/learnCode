/**
 * @author The_Beatles
 * @date 2021/10/31 11:39
 */

public class EndRunnable {

    private static class UserRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(threadName + " is run!");
            }
            System.out.println(threadName + " is isInterrupt flag is " + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new UserRunnable());
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }
}
