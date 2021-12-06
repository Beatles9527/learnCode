package cn.redblood.forkjoin;

import java.util.Random;

/**
 * 产生整型数组
 *
 * @author The_Beatles
 * @date 2021/12/5 14:56
 */

public class MakeArray {

    // 数组长度
    public static final int ARRAY_LENGTH = 40000000;

    public static int[] makeArray() {
        Random random = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            // 用随机数填充数组
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }

}
