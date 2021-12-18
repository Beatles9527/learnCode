package cn.redblood.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author The_Beatles
 * @date 2021/12/18 14:41
 */

public class UseAtomicInt {

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.get());
    }

}
