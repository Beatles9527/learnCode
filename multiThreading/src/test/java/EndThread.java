/**
 * @author The_Beatles
 * @date 2021/10/31 11:11
 */

public class EndThread {

    private static class UserThread extends Thread {

        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println(threadName + " is run!");
            }
            System.out.println(threadName + " is isInterrupt flag is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread endThread = new UserThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
