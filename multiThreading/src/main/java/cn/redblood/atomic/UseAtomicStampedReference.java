package cn.redblood.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试版本号
 *
 * @author The_Beatles
 * @date 2021/12/18 14:53
 */

public class UseAtomicStampedReference {

    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("Bill", 0);

    public static void main(String[] args) throws InterruptedException {
        final int oldStamp = asr.getStamp();
        final String oldReference = asr.getReference();

        System.out.println(oldReference + "========" + oldStamp);

        Thread rightStampThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "当前变量：" + oldReference + "当前版本戳：" + oldStamp
                    + "-" + asr.compareAndSet(oldReference, oldReference + "JAVA", oldStamp, oldStamp + 1));
        });

        Thread errorStampThread = new Thread(() -> {
            String reference = asr.getReference();
            System.out.println(Thread.currentThread().getName() + "当前变量：" + reference + "当前版本戳：" + asr.getStamp()
                    + "-" + asr.compareAndSet(reference, reference + "C", oldStamp, oldStamp + 1));
        });

        rightStampThread.start();
        rightStampThread.join();
        errorStampThread.start();
        errorStampThread.join();

        System.out.println(asr.getReference() + "========>>>>" + asr.getStamp());
    }

}
