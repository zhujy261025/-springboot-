package com.gec.springbootcourse.controller;

import com.gec.springbootcourse.req.RoleReq;
import com.gec.springbootcourse.service.RoleService;
import com.gec.springbootcourse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    // 查询角色
    @GetMapping("/queryRole")
    public Result queryRole(){
        return Result.success(roleService.queryRole());
    }

    @PutMapping("/editRole")
    public Result editRole(@RequestBody RoleReq roleReq){
        roleService.editRole(roleReq);
        return Result.success();
    }
}
