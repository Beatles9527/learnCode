/**
 * @author The_Beatles
 * @date 2021/10/31 16:25
 */

public class StartThread {

    private static class UserThread extends Thread {

        @Override
        public void run() {
            int i = 5;
            while (i > 0) {
                System.out.println(Thread.currentThread().getName() + "[" + i-- + "]: is run!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread userThread = new UserThread();
        userThread.setName("UserThread");
        userThread.run();
        userThread.start();
    }
}
