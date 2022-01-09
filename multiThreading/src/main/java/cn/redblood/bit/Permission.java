package cn.redblood.bit;

/**
 * 利用二进制运算规则设置用户权限
 *
 * @author The_Beatles
 * @date 2022/1/9 17:25
 */

public class Permission {

    /**
     * 是否允许查询：二进制第一位，0表示否，1表示是。0001:1
     */
    public static final int ALLOW_SELECT = 1 << 0;

    /**
     * 是否允许新增：二进制第二位，0表示否，1表示是。0010:2
     */
    public static final int ALLOW_INSERT = 1 << 1;

    /**
     * 是否允许修改：二进制第三位，0表示否，1表示是。0100:4
     */
    public static final int ALLOW_UPDATE = 1 << 2;

    /**
     * 是否允许删除：二进制第四位，0表示否，1表示是。1000:8
     */
    public static final int ALLOW_DELETE = 1 << 3;

    /**
     * 存储目前权限状态
     */
    private int flag;

    /**
     * 设置用户权限
     *
     * @param per
     */
    public void setPer(int per) {
        this.flag = per;
    }

    /**
     * 增加用户权限（一个或多个）
     *
     * @param per
     */
    public void enable(int per) {
        flag = flag | per;
    }

    /**
     * 删除用户权限（一个或多个）
     *
     * @param per
     */
    public void disable(int per) {
        flag = flag & ~per;
    }

    /**
     * 判定用户是否有权限
     *
     * @param per
     * @return
     */
    public boolean isAllow(int per) {
        return ((flag & per) == per);
    }

    /**
     * 判定用户没有权限
     *
     * @param per
     * @return
     */
    public boolean isNotAllow(int per) {
        return ((flag & per) == 0);
    }

    public static void main(String[] args) {
        int flag = 15;
        Permission permission = new Permission();
        // 1、给予用户全部权限
        permission.setPer(flag);
        // 2、测试禁止用户的新增和删除权限
        permission.disable(ALLOW_INSERT | ALLOW_DELETE);
        // 3、查看用户当前权限
        System.out.println("SELECT: " + permission.isAllow(ALLOW_SELECT));
        System.out.println("INSERT: " + permission.isAllow(ALLOW_INSERT));
        System.out.println("UPDATE: " + permission.isAllow(ALLOW_UPDATE));
        System.out.println("DELETE: " + permission.isAllow(ALLOW_DELETE));
    }

}
