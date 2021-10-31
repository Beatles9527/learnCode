import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author The_Beatles
 * @date 2021/10/31 10:12
 */

public class NewThread {

    private static class UserThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am extends Thread.");
        }
    }

    // 由于Java中只能实现单继承，如果采用方式一，则线程类就无法继承其他类了，所以实现Runnable接口创建线程的方式更受欢迎。
    private static class UserRun implements Runnable {

        @Override
        public void run() {
            System.out.println("I am implements Runnable.");
        }
    }

    // 在前面两种线程创建方式中，线程执行完毕后并没有返回值，而实现Callable接口创建线程可以在线程结束后返回一个值。
    private static class UserCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "I am implements Callable.";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Thread
        new UserThread().start();
        // Runnable
        new Thread(new UserRun()).start();
        // Callable
        FutureTask<String> futureTask = new FutureTask<>(new UserCall());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
