package cn.redblood.test;

import cn.redblood.dao.IUserDao;
import cn.redblood.io.Resources;
import cn.redblood.pojo.User;
import cn.redblood.sqlSession.SqlSession;
import cn.redblood.sqlSession.SqlSessionFactory;
import cn.redblood.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author charlie
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 调用
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//        List<User> userList = sqlSession.selectList("user.selectList");
//        for (User u : userList) {
//            System.out.println(u);
//        }

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
    }

}
