package cn.redblood.sqlSession;

import cn.redblood.pojo.Configuration;
import cn.redblood.pojo.MappedStatement;

import java.util.List;

/**
 * @author charlie
 */
public class DefaultSqlSession implements SqlSession{

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
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }
}
