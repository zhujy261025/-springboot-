package com.gec.springbootcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.springbootcourse.entity.Course;
import com.gec.springbootcourse.entity.Curelation;
import com.gec.springbootcourse.mapper.CurelationMapper;
import com.gec.springbootcourse.req.CourseReq;
import com.gec.springbootcourse.service.CourseService;
import com.gec.springbootcourse.mapper.CourseMapper;
import com.gec.springbootcourse.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.NestingKind;
import java.io.Reader;
import java.util.List;

/**
 *
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CurelationMapper curelationMapper;

    @Override
    public Result saveOrUpdateCourse(Course course) {
        if (StringUtils.isEmpty(course.getId())) {
            //无id则插入数据
            //设置课程的状态 1 标识可以选择
            course.setStatus("1");
            //设置课程id
            //substring = [2,10)数组下标十2 实际是从第3个元素开始取到10下标第11个数字
            course.setId(String.valueOf(Math.random()).substring(2,10));
            //添加数据
            int insert = baseMapper.insert(course);
            if (insert > 0){
                return Result.success();
            }else {
                return Result.error("40005","添加失败");
            }
            //判断添加结果
        }else{
            //有id则更新
            int update = baseMapper.updateById(course);
            if (update > 0){
                return Result.success();
            }else {
                return Result.error("40006","更新失败");
            }
        }
    }

    @Override
    public Result arranging(CourseReq req) {
        Course course = baseMapper.selectById(req.getId());
        String[] courseroom = req.getCourseroom();
        course.setCourseroom(StringUtils.join(courseroom,""));
        course.setCoursedate(StringUtils.join(req.getDate1(),"")
            +" "+StringUtils.join(req.getDate2(),""));
        int updated = baseMapper.updateById(course);
        if (updated > 0){
            return Result.success();
        }else {
            return Result.error("40008","修改失败");
        }
    }

//    @Override
//    public List<Curelation> queryCurelation() {
//        List<Curelation> curelations = curelationMapper.selectList(null);
//        return curelations;
//    }
//
//    @Override
//    public void listening(Curelation curelation) {
//        // 判断课程是否有听课人员
//        QueryWrapper<Curelation> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("courseid", curelation.getCourseid());
//        Curelation existingCurelation = curelationMapper.selectOne(queryWrapper);
//        if (existingCurelation != null) {
//            // 课程已经有听课人员
//            existingCurelation.setUserid(curelation.getUserid());
//            curelationMapper.updateById(existingCurelation);
//        } else {
//            Course course = courseMapper.selectById(curelation.getCourseid());
//            if (course != null) {
//                curelation.setType(course.getCoursename());
//                curelation.setId(String.valueOf(Math.random()).substring(2, 10));
//                curelationMapper.insert(curelation);
//            }
//        }
//    }
//
//    @Override
//    public void delCurelation(String id) {
//        curelationMapper.deleteById(id);
//    }
}




