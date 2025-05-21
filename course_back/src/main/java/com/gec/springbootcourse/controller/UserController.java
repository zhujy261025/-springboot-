package com.gec.springbootcourse.controller;

import com.alibaba.fastjson.JSONObject;
import com.gec.springbootcourse.entity.User;
import com.gec.springbootcourse.req.UserQueryReq;
import com.gec.springbootcourse.resp.UserResp;
import com.gec.springbootcourse.service.UserService;
import com.gec.springbootcourse.util.Constans;
import com.gec.springbootcourse.util.Result;
import com.gec.springbootcourse.util.SnowFlake;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SnowFlake snowFlake;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public Result<User> login(String userId, String password){
        System.out.println("账号信息："  + userId);
        System.out.println("密码信息："  + password);

        //拿用户的账号密码去数据库中作比较 如果能够查询出对应的账号信息则密码正确
        UserResp userResp = userService.login(userId,password);
        //生成token信息 用户登录令牌标识
        Long token = snowFlake.nextId();

        userResp.setToken(token.toString());

        //将token信息缓存到redis中 缓存用户的信息作为登陆的标识
        redisTemplate.opsForValue().set(token.toString(),JSONObject.toJSONString(userResp),24*60*60, TimeUnit.SECONDS);

        return Result.success(userResp);
    }

    @GetMapping("/loginOut/{token}")
    public Result loginOut(@PathVariable String token){
        redisTemplate.delete(token);
        return Result.success();
    }

    //findTeacher
    @GetMapping("/findTeacher")
    public Result findTeacher(@Valid UserQueryReq req){
        System.out.println(req);
        //设置查询条件 1查询老师 2查询主任
        req.setType(Constans.TEACHER);
        //调用业务代码逻辑 查询教师数据
        PageInfo<User> pageInfo = userService.findUser(req);
        //返回教师数据
        return Result.success(pageInfo);
    }

    @GetMapping("/findDirector")
    public Result findDirector(@Valid UserQueryReq req){
        System.out.println(req);
        //设置查询条件 1查询老师 2查询主任
        req.setType(Constans.DIRECTOR);
        //调用业务代码逻辑 查询主任数据
        PageInfo<User> pageInfo = userService.findUser(req);
        //返回主任数据
        return Result.success(pageInfo);
    }

    //saveUser
    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody User user){
        //RequestBody这个注解的作用就是将提交过来的json数据序列化成对象
        System.out.println(user);
        Result result = userService.saveOrupdateUser(user);
        return result;
    }

    @DeleteMapping("/delUser/{id}")
    public Result delUser(@PathVariable String id){
        System.out.println(id);
        Result result = userService.deleteUser(id);
        return result;
    }

    //查询所有老师
    @GetMapping("/queryTeacher")
    public Result queryTeacher(){
        //直接调用查询业务逻辑代码
        List<User> teachers = userService.queryUser();
        return Result.success(teachers);
    }

    @PostMapping("/upPwd")
    public Result upPwd(String oldPwd, String newPwd, HttpServletRequest request){
        System.out.println(oldPwd+ "[["+newPwd);

        String token = request.getHeader("token");
        User user = JSONObject.parseObject((String) redisTemplate.opsForValue().get(token),User.class);
        System.out.println("--"+user);
        if (!user.getPassword().equals(oldPwd)){
            return Result.success("旧密码不正确");
        }else {
            user.setPassword(newPwd);
            userService.upPwd(user);
            return Result.success("修改密码成功");
        }
    }
}