package cn.redblood.dao;

import cn.redblood.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/22 09:58
 */

public interface IUserDao {

    /**
     * 查询全部用户
     *
     * @return
     * @throws IOException
     */
    List<User> findAll() throws IOException;

    /**
     * 多条件组合查询：演示if
     *
     * @param user 查询条件
     * @return
     */
    List<User> findByCondition(User user) throws IOException;

    /**
     * 多值查询：演示foreach
     *
     * @param ids
     * @return
     */
    List<User> findByIds(int[] ids);
}
