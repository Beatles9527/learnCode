package cn.redblood.productconsumer;

import java.util.LinkedList;

/**
 * @author The_Beatles
 * @date 2021/12/5 15:56
 */

public class ProductConsumer {

    private final static int MAX_SIZE = 10;
    private final static int MIN_SIZE = 0;

    private final LinkedList<String> linkedList = new LinkedList<>();

    public void product() throws InterruptedException {
        synchronized (linkedList) {
            while (linkedList.size() == MAX_SIZE) {
                System.out.println("【生产者】当前库存已满，无法进行生产...");
                linkedList.wait();
            }
            linkedList.push("AirPodsPro");
            System.out.println("【生产者】制造了一件产品...,当前库存为：" + linkedList.size());
            linkedList.notifyAll();
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (linkedList) {
            while (linkedList.size() == MIN_SIZE) {
                System.out.println("【消费者】当前库存无货，无法进行消费...");
                linkedList.wait();
            }
            linkedList.pop();
            System.out.println("【消费者】消费了一件产品...，当前库存为： " + linkedList.size());
            linkedList.notifyAll();
        }
    }

}
