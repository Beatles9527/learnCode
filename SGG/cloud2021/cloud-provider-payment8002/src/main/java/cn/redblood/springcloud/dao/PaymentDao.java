package cn.redblood.springcloud.dao;

import cn.redblood.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author charlie
 */
@Mapper
//@Repository 不用Spring的
public interface PaymentDao {

    /**
     * 创建
     *
     * @param payment ....
     * @return ...
     */
    int create(Payment payment);

    /**
     * 根据id查询
     *
     * @param id ....
     * @return ...
     */
    Payment getPaymentById(@Param("id") Long id);
}
