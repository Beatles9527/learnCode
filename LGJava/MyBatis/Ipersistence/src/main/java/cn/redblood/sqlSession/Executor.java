package cn.redblood.sqlSession;

import cn.redblood.pojo.Configuration;
import cn.redblood.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @author charlie
 */
public interface Executor {

    /**
     * 执行查询方法
     *
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, Exception;
}
