package cn.redblood.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现一个自己的类ReentrantLock
 *
 * @author The_Beatles
 * @date 2022/1/2 18:38
 */

public class SelfLock implements Lock {

    /**
     * state 表示获取到锁 state=1 获取到了锁，state=0，表示这个锁当前没有线程拿到
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 是否占用
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return super.getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (super.compareAndSetState(0, 1)) {
                super.setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new UnsupportedOperationException("释放失败！");
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition(){
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
