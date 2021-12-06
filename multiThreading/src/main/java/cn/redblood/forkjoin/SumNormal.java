package cn.redblood.forkjoin;

import cn.redblood.utils.SleepTools;

/**
 * @author The_Beatles
 * @date 2021/12/5 15:05
 */

public class SumNormal {

    public static void main(String[] args) {
        int count = 0;

        int[] src = MakeArray.makeArray();
        long start = System.currentTimeMillis();
        for (int j : src) {
//            SleepTools.ms(1);
            count = count + j;
        }
        System.out.println("The count is " + count + " spend time: " + (System.currentTimeMillis() - start) + "ms");
    }

}
