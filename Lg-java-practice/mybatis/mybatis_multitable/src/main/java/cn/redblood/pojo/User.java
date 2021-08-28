package cn.redblood.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/22 16:06
 */

@Table(name = "user")
public class User implements Serializable {

    /**
     * @Id 对应的是注解ID
     * @GeneratedValue 设置主键的生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    /**
     * @Column 数据库对应字段映射(如果不一致 ， 可以使用这个注解进行关联)
     */
    @Column(name = "username")
    private String username;

//    private List<Order> orderList = new ArrayList<>();
//
//    private List<Role> roleList = new ArrayList<>();

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", roleList=" + roleList +
//                '}';
//    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", orderList=" + orderList +
//                '}';
//    }


//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                '}';
//    }

//    public List<Role> getRoleList() {
//        return roleList;
//    }
//
//    public void setRoleList(List<Role> roleList) {
//        this.roleList = roleList;
//    }
//
//    public List<Order> getOrderList() {
//        return orderList;
//    }
//
//    public void setOrderList(List<Order> orderList) {
//        this.orderList = orderList;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
