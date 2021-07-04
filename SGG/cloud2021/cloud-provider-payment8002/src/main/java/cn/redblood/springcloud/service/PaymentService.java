package cn.redblood.springcloud.service;

import cn.redblood.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author charlie
 */
public interface PaymentService {

    /**
     * 创建
     *
     * @param payment ...
     * @return ...
     */
    int create(Payment payment);

    /**
     * 查询
     *
     * @param id ...
     * @return ...
     */
    Payment getPaymentById(@Param("id") Long id);
}
