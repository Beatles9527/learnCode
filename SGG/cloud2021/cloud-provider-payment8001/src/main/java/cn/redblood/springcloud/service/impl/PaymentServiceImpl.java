package cn.redblood.springcloud.service.impl;

/**
 * @author charlie
 */

import cn.redblood.springcloud.dao.PaymentDao;
import cn.redblood.springcloud.entity.Payment;
import cn.redblood.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    /**
     * 创建
     *
     * @param payment ...
     * @return ...
     */
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    /**
     * 查询
     *
     * @param id ...
     * @return ...
     */
    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
