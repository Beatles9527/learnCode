package cn.redblood.goods;

import cn.redblood.utils.SleepTools;

/**
 * 内置锁实现商品服务接口
 *
 * @author The_Beatles
 * @date 2021/12/18 16:09
 */

public class UseSyn implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        SleepTools.ms(5);
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);
    }
}
