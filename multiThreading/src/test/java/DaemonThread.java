/**
 * @author The_Beatles
 * @date 2021/10/31 16:40
 */

public class DaemonThread {

    private static class UserThread extends Thread {

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " is run!");
                }
                System.out.println(Thread.currentThread().getName() + " is isInterrupt flag is " + isInterrupted());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("。。。。。。。。。finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread userThread = new UserThread();
        userThread.setDaemon(true); // 在start方法前使用
        userThread.start();
        Thread.sleep(5);
//        userThread.interrupt();
    }
}
