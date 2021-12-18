package cn.redblood.goods;

import cn.redblood.utils.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 ReadWriteLock
 *
 * @author The_Beatles
 * @date 2021/12/18 16:52
 */

public class UseRwLock implements GoodsService {
    private GoodsInfo goodsInfo;

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁
    private final Lock getLock = lock.readLock();
    // 写锁
    private final Lock setLock = lock.writeLock();

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        } finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }
    }
}
