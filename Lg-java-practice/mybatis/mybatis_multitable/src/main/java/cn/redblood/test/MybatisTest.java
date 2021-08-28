package cn.redblood.test;

import cn.redblood.mapper.IOrderMapper;
import cn.redblood.mapper.IUserMapper;
import cn.redblood.mapper.UserMapper;
import cn.redblood.pojo.Order;
import cn.redblood.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/22 16:29
 */

public class MybatisTest {

    /**
     * 一对一查询
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }

    /**
     * 一对多查询
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user.getUsername());
//            System.out.println(user.getOrderList());
            System.out.println("==========");
        }
    }

    /**
     * 多对多查询
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> roleList = mapper.findAllUserAndRole();
        for (User user : roleList) {
            System.out.println(user);
        }
    }

    private IUserMapper userMapper;
    private IOrderMapper orderMapper;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
        orderMapper = sqlSession.getMapper(IOrderMapper.class);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setId(4);
        user.setUsername("测试数据");
        userMapper.addUser(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(4);
        user.setUsername("测试000数据");
        userMapper.updateUser(user);
    }

    @Test
    public void selectUser() {
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void oneToOne() {
        List<Order> list = orderMapper.findOrderAndUser01();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void oneToMany() {
        List<User> userList = userMapper.findAll01();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void manyToMany() {
        List<User> userList = userMapper.findAllUserAndRole01();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void mapperTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        User one = mapper.selectOne(user);
        System.out.println(one);

        // example方法
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", 1);
        List<User> users = mapper.selectByExample(example);
        for (User user1 : users) {
            System.out.println("example方法:===>" + user1);
        }
    }
}
