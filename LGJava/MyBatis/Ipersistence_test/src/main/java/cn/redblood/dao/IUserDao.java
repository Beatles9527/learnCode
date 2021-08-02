package cn.redblood.dao;

import cn.redblood.pojo.User;

import java.util.List;

/**
 * @author charlie
 */
public interface IUserDao {

    /**
     * 查询所有用户¬
     *
     * @return
     */
    List<User> findAll() throws Exception;

    /**
     * 根据条件进行用户查询
     *
     * @param user
     * @return
     */
    User findByCondition(User user) throws Exception;
}
