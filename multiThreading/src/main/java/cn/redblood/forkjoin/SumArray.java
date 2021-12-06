package cn.redblood.forkjoin;

import cn.redblood.utils.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author The_Beatles
 * @date 2021/12/5 14:59
 */

public class SumArray {

    private static class SumTask extends RecursiveTask<Integer> {

        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        // 实际统计的数组
        private int[] src;
        // 开始统计的下标
        private int fromIndex;
        // 统计到哪里结束的下标
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
//                    SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            } else {
                // fromIndex ... mid.....toIndex
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    // 疑问？为什么单线程的时间比多线程短？
    // 上下文切换
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();
        SumTask innerFind = new SumTask(src, 0, src.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(innerFind);
        System.out.println("Task is Running......");

        System.out.println("The count is " + innerFind.join() + " spend time: " + (System.currentTimeMillis() - start) + "ms");
    }

}
