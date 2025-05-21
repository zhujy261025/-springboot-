package com.gec.springbootcourse.service;

import com.gec.springbootcourse.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.springbootcourse.req.UserQueryReq;
import com.gec.springbootcourse.resp.UserResp;
import com.gec.springbootcourse.util.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {

    UserResp login(String userId, String password);

    PageInfo<User> findUser(UserQueryReq req);

    Result saveOrupdateUser(User user);

    Result deleteUser(String id);

    List<User> queryUser();

    void upPwd(User user);
}
