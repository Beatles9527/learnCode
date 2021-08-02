package cn.redblood.dao;

import cn.redblood.io.Resources;
import cn.redblood.pojo.User;
import cn.redblood.sqlSession.SqlSession;
import cn.redblood.sqlSession.SqlSessionFactory;
import cn.redblood.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author charlie
 */
public class UserDaoImpl implements IUserDao{

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() throws Exception {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList("user.selectList");
    }

    /**
     * 根据条件进行用户查询
     *
     * @param user
     * @return
     */
    @Override
    public User findByCondition(User user) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用
        return sqlSession.selectOne("user.selectOne", user);
    }
}
