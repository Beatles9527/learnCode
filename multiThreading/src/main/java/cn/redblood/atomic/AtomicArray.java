package cn.redblood.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author The_Beatles
 * @date 2021/12/18 15:13
 */

public class AtomicArray {
    static int[] value = new int[]{1, 2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
