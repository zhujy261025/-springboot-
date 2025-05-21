package com.gec.springbootcourse.resp;

import com.gec.springbootcourse.entity.User;
import lombok.Data;

@Data
public class UserResp extends User {

    private String token;   //用户令牌

    private String power;   //用户权限
}
