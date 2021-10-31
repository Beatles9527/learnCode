import java.util.concurrent.TimeUnit;

/**
 * @author The_Beatles
 * @date 2021/10/31 17:48
 */

public class SyncClzAndInst {

    // 使用类🔐的线程
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    // 使用对象🔐的线程
    private static class InstanceSyn implements Runnable {
        private SyncClzAndInst syncClzAndInst;

        public InstanceSyn(SyncClzAndInst syncClzAndInst) {
            this.syncClzAndInst = syncClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + syncClzAndInst);
            syncClzAndInst.instance();
        }
    }

    // 使用对象🔐的线程
    private static class Instance2Syn implements Runnable {
        private SyncClzAndInst syncClzAndInst;

        public Instance2Syn(SyncClzAndInst syncClzAndInst) {
            this.syncClzAndInst = syncClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running..." + syncClzAndInst);
            syncClzAndInst.instance2();
        }
    }

    // 使用对象🔐的方法
    private synchronized void instance() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance is going..." + this);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance end..." + this);
    }

    // 使用对象🔐的方法
    private synchronized void instance2() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance2 is going..." + this);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synInstance2 end..." + this);
    }

    // 使用类🔐的方法
    private static synchronized void synClass() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synClass going...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synClass end...");
    }

    public static void main(String[] args) {
        SyncClzAndInst syncClzAndInst = new SyncClzAndInst();
        Thread t1 = new Thread(new InstanceSyn(syncClzAndInst));

//        SyncClzAndInst syncClzAndInst2 = new SyncClzAndInst();
//        Thread t2 = new Thread(new Instance2Syn(syncClzAndInst));

        t1.start();
//        t2.start();

        SynClass synClass = new SynClass();
        synClass.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
