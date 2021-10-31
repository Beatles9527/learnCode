import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/29 11:18
 */

public class MybatisTest {

  /**
   * 传统方式
   *
   * @throws IOException
   */
  public void test1() throws IOException {
    // 1.读取配置文件，读成字节输入流，注意：现在还没解析
    InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    // 2.解析配置文件，封装configuration对象，创建DefaultSqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    // 3.生产了DefaultSqlSession实例对象，设置了事务不自动提交，完成了Executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();
    // 4.(1)根据StatementId从Configuration的map集合中获取到了指定的MappedStatement对象
    //   (2)将查询任务委派给Executor执行器
    List<Object> objects = sqlSession.selectList("namespace.id");
  }

  /**
   * mapper代理方式
   */
  public void test2() throws IOException {

  }
}
