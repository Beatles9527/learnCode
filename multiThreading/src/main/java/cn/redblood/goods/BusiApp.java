package cn.redblood.goods;

import cn.redblood.utils.SleepTools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 对商品进行业务的应用
 *
 * @author The_Beatles
 * @date 2021/12/18 16:13
 */

public class BusiApp {

    static final int readWriteRatio = 10;

    static final int minThreadCount = 3;

    static CountDownLatch latch = new CountDownLatch(1);

    /**
     * 读操作
     */
    private static class GetThread implements Runnable {
        private GoodsService goodsService;

        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            // 让读写线程同时运行
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodsService.getNum();
            }
            System.out.println(Thread.currentThread().getName() + "读取商品数据耗时："
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }

    /**
     * 写操作
     */
    private static class SetThread implements Runnable {
        private GoodsService goodsService;

        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            // 让读写线程同时运行
//            try {
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                SleepTools.ms(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName() + "写商品数据耗时："
                    + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("AirPodPro", 10000, 10000);
//        GoodsService goodsService = new UseSyn(goodsInfo);
        GoodsService goodsService = new UseRwLock(goodsInfo);
        for (int i = 0; i < minThreadCount; i++) {
            Thread setT = new Thread(new SetThread(goodsService));
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getT = new Thread(new GetThread(goodsService));
                getT.start();
            }
            SleepTools.ms(100);
            setT.start();

        }
//        latch.countDown();
    }
}
