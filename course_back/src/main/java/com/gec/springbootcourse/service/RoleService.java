package com.gec.springbootcourse.service;

import com.gec.springbootcourse.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.springbootcourse.req.RoleReq;

import java.util.List;

/**
 *
 */
public interface RoleService extends IService<Role> {

    public List<RoleReq> queryRole();

    void editRole(RoleReq roleReq);
}
