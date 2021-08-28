package cn.redblood.mapper;

import cn.redblood.pojo.Order;
import cn.redblood.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/22 16:45
 */

public interface IOrderMapper {

    /**
     * 查询订单的同时还查询该订单所属的用户
     *
     * @return
     */
    List<Order> findOrderAndUser();

    /**
     * 查询订单的同时还查询该订单所属的用户
     *
     * @return
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "total", column = "total"),
            @Result(property = "user", column = "uid", javaType = User.class, one = @One(select = "cn.redblood.mapper.IUserMapper.findUserById"))
    })
    @Select(value = "select * from orders")
    List<Order> findOrderAndUser01();

    /**
     * 根据UID查询订单信息
     *
     * @param uid
     * @return
     */
    @Select(value = "select * from orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);
}
