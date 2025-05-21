package com.gec.springbootcourse.mapper;

import com.gec.springbootcourse.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.springbootcourse.req.RoleReq;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Entity com.gec.springbootcourse.entity.Role
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT * FROM role, power WHERE role.powerid = power.roleid")
    List<RoleReq> queryRoleAndPower();
}




