package com.gec.springbootcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.springbootcourse.entity.Power;
import com.gec.springbootcourse.entity.Role;
import com.gec.springbootcourse.mapper.PowerMapper;
import com.gec.springbootcourse.req.RoleReq;
import com.gec.springbootcourse.service.RoleService;
import com.gec.springbootcourse.mapper.RoleMapper;
import com.gec.springbootcourse.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;


    @Override
    public List<RoleReq> queryRole() {
        return roleMapper.queryRoleAndPower();
    }

    @Override
    public void editRole(RoleReq roleReq) {
        // 更新 Role 表
        Role role = CopyUtil.copy(roleReq, Role.class);
        roleMapper.updateById(role); // MyBatis-Plus 更新主键方法

        // 更新 Power 表
        Power power = new Power();
        power.setPower(roleReq.getPower());

        // 使用 UpdateWrapper 进行条件更新
        UpdateWrapper<Power> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("roleid", roleReq.getPowerid());

        powerMapper.update(power, updateWrapper); // MyBatis-Plus 条件更新方法
    }
}




