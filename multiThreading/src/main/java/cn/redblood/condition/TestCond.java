package cn.redblood.condition;

/**
 * @author The_Beatles
 * @date 2021/12/18 18:01
 */

public class TestCond {
    private static ExpressCond express = new ExpressCond(0, ExpressCond.CITY);

    /**
     * 检查里程数变化的线程，不满足条件，线程一直等待
     */
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /**
     * 检查地点变化的线程，不满足条件，线程一直等待
     */
    private static class CheckSite extends Thread{
        @Override
        public void run(){
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 三个线程
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        // 里程数的变化
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }

        Thread.sleep(1000);
        // 快递地点的变化
        express.changeKm();
    }
}
