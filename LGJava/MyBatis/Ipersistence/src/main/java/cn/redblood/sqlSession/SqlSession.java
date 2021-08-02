package cn.redblood.sqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * @author charlie
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementId, Object... params) throws SQLException, Exception;

    /**
     * 查询单个
     *
     * @param statementId
     * @param params
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object... params) throws Exception;

    /**
     *
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> mapperClass);
}
