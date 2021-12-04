/**
 * join方法的使用
 *
 * @author The_Beatles
 * @date 2021/12/4 15:59
 */

public class UseJoin {

    static class JumpQueue implements Runnable {

        // 用来插队的线程
        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName() + " jump a queue the thread: " + thread.getName());
            thread.start();
            previous = thread;
        }

        // 让主线程休眠2秒
        SleepTools.second(2);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

}
