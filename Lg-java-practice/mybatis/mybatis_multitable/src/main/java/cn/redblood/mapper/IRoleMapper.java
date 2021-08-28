package cn.redblood.mapper;

import cn.redblood.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author The_Beatles
 * @date 2021/8/28 10:35
 */

public interface IRoleMapper {

    /**
     * 根据用户ID获取用户角色信息
     *
     * @param uid
     * @return
     */
    @Select(value = "select * from sys_role r,sys_user_role ur where r.id = ur.roleid and ur.userid = #{uid}")
    List<Role> findRoleByUid(Integer uid);
}
