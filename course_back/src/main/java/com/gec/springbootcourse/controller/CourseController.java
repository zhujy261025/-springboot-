package com.gec.springbootcourse.controller;

import com.gec.springbootcourse.entity.Course;
import com.gec.springbootcourse.entity.Curelation;
import com.gec.springbootcourse.req.CourseReq;
import com.gec.springbootcourse.service.CourseService;
import com.gec.springbootcourse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/queryCourse")
    public Result queryCourse(){
        //直接调用mybatis plus提供的基础代码实现
        List<Course> list = courseService.list();
        //将查询结果返回给用户
        return Result.success(list);
    }

    @PostMapping("/saveCourse")
    public Result saveCourse(@RequestBody Course course){
        System.out.println(course);
        //编写业务逻辑代码 判断到底进行
        Result result = courseService.saveOrUpdateCourse(course);
        return Result.success();
    }

    @DeleteMapping("/delCourse/{id}")
    public Result delCourse(@PathVariable String id){
        if (courseService.removeById(id)){
            return Result.success();
        }else {
            return Result.error("40007","删除失败");
        }
    }

    //arranging
    @PostMapping("/arranging")
    public Result arranging(@RequestBody CourseReq req){
        System.out.println(req);
        //排课 需要将排课信息更新到对应的课程中
        Result result = courseService.arranging(req);
        return Result.success();
    }

//    @GetMapping("/queryCurelation")
//    public Result queryCurelation(){
//        List<Curelation> list =  courseService.queryCurelation();
//        return Result.success(list);
//    }
//
//    //设置听课安排
//    @PostMapping("/listening")
//    public Result listening(@RequestBody Curelation curelation ){
//        courseService.listening(curelation);
//        return Result.success();
//    }
//
//    // 删除听课安排
//    @DeleteMapping("/delCurelation/{id}")
//    public Result delCurelation(@PathVariable String id){
//        courseService.delCurelation(id);
//        return Result.success();
//    }
}
