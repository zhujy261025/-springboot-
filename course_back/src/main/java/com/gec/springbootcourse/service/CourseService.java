package com.gec.springbootcourse.service;

import com.gec.springbootcourse.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.springbootcourse.entity.Curelation;
import com.gec.springbootcourse.req.CourseReq;
import com.gec.springbootcourse.util.Result;

import java.util.List;

/**
 *
 */
public interface CourseService extends IService<Course> {

    Result saveOrUpdateCourse(Course course);

    Result arranging(CourseReq req);

//    List<Curelation> queryCurelation();
//
//    void listening(Curelation curelation);
//
//    void delCurelation(String id);
}
