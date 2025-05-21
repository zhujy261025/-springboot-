package com.gec.springbootcourse.controller;

import com.gec.springbootcourse.entity.Course;
import com.gec.springbootcourse.service.CourseService;
import com.gec.springbootcourse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CourseService courseService;

    @RequestMapping("/hello")
    public Result<Course> hello(){
        List<Course> list = courseService.list();
        return Result.success(list);
    }
}
