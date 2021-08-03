package cn.redblood.sqlSession;

import cn.redblood.pojo.Configuration;
import cn.redblood.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author charlie
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {

        // 将要完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return simpleExecutor.query(configuration, mappedStatement, params);

    }

    /**
     * 查询单个
     *
     * @param statementId
     * @param params
     * @return
     */
    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = this.selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    /**
     * @param mapperClass
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        // 使用JDK動態代理來为DAO接口生成代理对象，并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 // 根据不同情况，来调用selectList或者selectOne
                // 准备参数 1：statementId ：sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名： findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                // 准备参数 2：param：args
                // 获取被调用方法的返回值
                Type genericReturnType = method.getGenericReturnType();
                // TODO 判断是否进行了，泛型参数化！！！！！
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }
                return selectOne(statementId,args);
            }
        });
        return (T) proxyInstance;
    }
}
