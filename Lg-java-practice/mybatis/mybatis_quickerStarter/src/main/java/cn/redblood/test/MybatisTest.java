package cn.redblood.test;

import cn.redblood.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/7 12:46 下午
 */

public class MybatisTest {

    @Test
    public void test01() throws IOException {
        // 1.获取配置文件及sql信息
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.构造sql工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.开启sqlSession
        // 默认开启一个事务，但是该事务不会自动自动提交，在进行增删改查是，需要手动提交。。
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql
        List<User> users = sqlSession.selectList("user.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        // 5.关闭会话
        sqlSession.close();
    }

    @Test
    public void test02() throws IOException {
        // 1.获取配置文件及sql信息
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.构造sql工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.开启sqlSession(当openSession入参设置为true时，事务自动提交！)
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 4.设置参数
        User user = new User();
        user.setId(4);
        user.setUsername("田东");
        // 5.插入数据
        sqlSession.insert("user.saveUser", user);
        // TODO: 2021/8/7 在做增删改的操作时，需要提交事务！！！
        // 6.提交事务
//        sqlSession.commit();

        // 5.关闭会话
        sqlSession.close();
    }

    @Test
    public void test03() throws IOException {
        // 1.获取配置文件及sql信息
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.构造sql工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.开启sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.设置参数
        User user = new User();
        user.setId(2);
        user.setUsername("金东");
        // 5.插入数据
        sqlSession.update("user.updateUser", user);
        // TODO: 2021/8/7 在做增删改的操作时，需要提交事务！！！
        // 6.提交事务
        sqlSession.commit();

        // 5.关闭会话
        sqlSession.close();
    }

    @Test
    public void test04() throws IOException {
        // 1.获取配置文件及sql信息
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2.构造sql工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 3.开启sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5.删除数据
        sqlSession.delete("user.deleteUser", 2);
        // TODO: 2021/8/7 在做增删改的操作时，需要提交事务！！！
        // 6.提交事务
        sqlSession.commit();

        // 5.关闭会话
        sqlSession.close();
    }
}
