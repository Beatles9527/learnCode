package cn.redblood.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author The_Beatles
 * @date 2021/12/18 15:43
 */

public class LockDemo {

    private Lock lock = new ReentrantLock();

    private int count;

    public void increment(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }

    public synchronized void incr2(){
        count++;
    }

    public synchronized void incr3(){
        incr2();
    }

}
