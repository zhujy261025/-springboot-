package com.gec.springbootcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.springbootcourse.entity.Power;
import com.gec.springbootcourse.entity.User;
import com.gec.springbootcourse.mapper.PowerMapper;
import com.gec.springbootcourse.req.UserQueryReq;
import com.gec.springbootcourse.resp.UserResp;
import com.gec.springbootcourse.service.UserService;
import com.gec.springbootcourse.mapper.UserMapper;
import com.gec.springbootcourse.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    PowerMapper powerMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserResp login(String userId, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",userId);
        wrapper.eq("password",password);
        User user = baseMapper.selectOne(wrapper);

        if (user == null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_ERROR);
        }else {
            UserResp userResp = new UserResp();
            userResp = CopyUtil.copy(user,UserResp.class);
            Power power = powerMapper.selectById(user.getType());
            userResp.setPower(power.getPower());
            return userResp;
        }
    }

    @Override
    public PageInfo<User> findUser(UserQueryReq req) {
        //设置查询参数(设置查询类型作为查询条件)
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type",req.getType());
        //设置 是否进行模糊查询
        if (req.getName() != null){
            wrapper.like("name",req.getName());
        }
        //设置分页查询开始
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        //查询数据
        List<User> users = baseMapper.selectList(wrapper);
        //将查询结果放入分页工具对象中
        PageInfo<User>pageInfo = new PageInfo<>(users);
        //返回结果
        return pageInfo;
    }

    @Override
    public Result saveOrupdateUser(User user) {
        User selectById = baseMapper.selectById(user.getId());
        if (selectById == null){
            int insert = baseMapper.insert(user);
            if (insert > 0){
                return Result.success();
            }else {
                return Result.error("40001","添加失败");
            }
        }else {
            int updaate = baseMapper.updateById(user);
            if (updaate > 0){
                return Result.success();
            }else {
                return Result.error("40002","修改失败");
            }
        }
    }

    @Override
    public Result deleteUser(String id) {
        //调用数据库访问层代码的删除 方法看看是否删除成功
        int delete = baseMapper.deleteById(id);
        if (delete > 0){
            return Result.success();
        }else {
            return Result.error("40003","删除失败");
        }
    }

    @Override
    public List<User> queryUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constans.TEACHER);
        List<User> users = baseMapper.selectList(queryWrapper);
        return users;
    }

    @Override
    public void upPwd(User user) {
        userMapper.updateById(user);
    }
}




