package com.gec.springbootcourse.controller;

import com.gec.springbootcourse.entity.Notice;
import com.gec.springbootcourse.service.NoticeService;
import com.gec.springbootcourse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/queryNotice")
    public Result queryNotice(){
        List<Notice> notices = noticeService.queryNotice();
        return Result.success(notices);
    }

    @PostMapping("/saveNotice")
    public Result saveUser(@RequestBody @Valid Notice notice){
        noticeService.saveNotice(notice);
        return Result.success();
    }

    @DeleteMapping("/delNotice/{id}")
    public Result delUser(@PathVariable  String id){
        System.out.println(id);
        noticeService.delNotice(id);
        return Result.success();
    }
}
