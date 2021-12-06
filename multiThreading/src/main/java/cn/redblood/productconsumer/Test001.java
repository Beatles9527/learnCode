package cn.redblood.productconsumer;

import cn.redblood.utils.SleepTools;

/**
 * @author The_Beatles
 * @date 2021/12/5 16:13
 */

public class Test001 {

    private final static int MAX_VALUE = 100;

    public static void main(String[] args) {
        ProductConsumer productConsumer = new ProductConsumer();
        // 生产者
        new Thread(() -> {
            for (int i = 0; i < MAX_VALUE; i++) {
                try {
                    productConsumer.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("生产异常。。。");
                }
            }
        }).start();
        // 消费者
        new Thread(() -> {
            for (int i = 0; i < MAX_VALUE; i++) {
                try {
                    SleepTools.ms(10);
                    productConsumer.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("消费异常。。。");
                }
            }
        }).start();
    }
}
