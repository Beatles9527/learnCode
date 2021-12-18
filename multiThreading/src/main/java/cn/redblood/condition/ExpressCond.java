package cn.redblood.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author The_Beatles
 * @date 2021/12/18 17:05
 */

public class ExpressCond {

    public final static String CITY = "ShangHai";

    private int km;

    private String site;

    // 1、两个锁
//    private Lock kmLock = new ReentrantLock();
//    private Lock siteLock = new ReentrantLock();

//    private Condition keCond = kmLock.newCondition();
//    private Condition siteCond = siteLock.newCondition();
    // 2、可以使用一个锁
    private Lock lock = new ReentrantLock();
    private Condition keCond = lock.newCondition();
    private Condition siteCond = lock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /**
     * 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理
     */
    public synchronized void changeKm() {
//        kmLock.lock();
        lock.lock();
        try {
            this.km = 101;
            keCond.signal();
        } finally {
//            kmLock.unlock();
            lock.unlock();
        }
    }

    /**
     * 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理
     */
    public synchronized void changeSite() {
//        siteLock.lock();
        lock.lock();
        try {
            this.site = "BeiJing";
            siteCond.signal();
        } finally {
//            siteLock.unlock();
            lock.unlock();
        }
    }

    public void waitKm() {
//        kmLock.lock();
        lock.lock();
        try {
            while (this.km < 100) {
                try {
                    keCond.await();
                    System.out.println("check km thread[" + Thread.currentThread().getId() + "] is be notified.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
//            kmLock.unlock();
            lock.unlock();
        }
        System.out.println("the km is " + this.km + ", I will change db.");
    }

    public void waitSite() {
//        siteLock.lock();
        lock.lock();
        try {
            while (CITY.equals(this.site)) {
                try {
                    siteCond.await();
                    System.out.println("check site thread[" + Thread.currentThread().getId() + "] is be notified.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
//            siteLock.unlock();
            lock.unlock();
        }
        System.out.println("the site is " + this.site + ", I will call user.");
    }

}
