/**
 * @author The_Beatles
 * @date 2021/10/31 11:43
 */

public class HasInterruptException {

    private static class UserThread extends Thread {

        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " is isInterrupt flag is " + isInterrupted());
                    e.printStackTrace();
                    interrupt();
                }
                System.out.println(threadName + " is run!");
            }
            System.out.println(threadName + " is isInterrupt flag is ---" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread endThread = new UserThread("HasInterruptException");
        endThread.start();
        Thread.sleep(100);
        endThread.interrupt();
    }
}
