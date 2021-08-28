package cn.redblood.mapper;

import cn.redblood.pojo.Order;
import cn.redblood.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.impl.PerpetualCache;

import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/22 16:16
 */
@CacheNamespace(implementation = PerpetualCache.class) // 开启二级缓存
public interface IUserMapper {

    /**
     * 查询订单的同时还查询该订单所属的用户
     *
     * @return
     */
    List<Order> findOrderAndUser();

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<User> findAll();

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @Select(value = "select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "orderList", column = "id", javaType = List.class, many = @Many(select = "cn.redblood.mapper.IOrderMapper.findOrderByUid"))
    })
    List<User> findAll01();

    /**
     * 查询所有用户及每个用户关联的角色信息
     *
     * @return
     */
    List<User> findAllUserAndRole();

    /**
     * 查询所有用户及每个用户关联的角色信息
     *
     * @return
     */
    @Select(value = "select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roleList", column = "id", javaType = List.class, many = @Many(select = "cn.redblood.mapper.IRoleMapper.findRoleByUid"))
    })
    List<User> findAllUserAndRole01();

    /**
     * 注解方式添加用户信息
     *
     * @param user
     */
    @Insert(value = "insert into user values(#{id},#{username})")
    void addUser(User user);

    /**
     * 注解方式更新用户信息
     *
     * @param user
     */
    @Update(value = "update user set username = #{username} where id = #{id}")
    void updateUser(User user);

    /**
     * 注解方式查询全部用户信息
     *
     * @return
     */
    @Select(value = "select * from user")
    List<User> selectUser();

    /**
     * 注解方式删除用户信息
     *
     * @param id
     */
    @Delete(value = "delete from user where id = #{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Select(value = "select * from user where id = #{id}")
    User findUserById(Integer id);

}
