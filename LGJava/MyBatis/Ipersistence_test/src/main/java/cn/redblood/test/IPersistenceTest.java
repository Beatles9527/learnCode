package cn.redblood.test;

import cn.redblood.io.Resources;
import cn.redblood.pojo.User;
import cn.redblood.sqlSession.SqlSession;
import cn.redblood.sqlSession.SqlSessionFactory;
import cn.redblood.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

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
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);
    }

}
