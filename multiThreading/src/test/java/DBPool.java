import java.sql.Connection;
import java.util.LinkedList;

/**
 * 实现一个数据库连接池
 *
 * @author The_Beatles
 * @date 2021/11/28 16:16
 */

public class DBPool {

    /**
     * 数据库池容器
     */
    private static LinkedList<Connection> pool = new LinkedList<>();

    public DBPool(int initSize) {
        if (initSize > 0) {
            for (int i = 0; i < initSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 在mills时间内还拿不到数据库连接，返回一个null
     *
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConn(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long overtime = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait();
                    remain = overtime - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    /**
     * 放回数据库连接
     *
     * @param conn
     */
    public void releaseConn(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                pool.notifyAll();
            }
        }
    }

}
