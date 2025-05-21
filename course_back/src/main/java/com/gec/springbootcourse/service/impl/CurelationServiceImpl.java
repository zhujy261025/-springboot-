package com.gec.springbootcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.springbootcourse.entity.Curelation;
import com.gec.springbootcourse.mapper.CourseMapper;
import com.gec.springbootcourse.service.CurelationService;
import com.gec.springbootcourse.mapper.CurelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CurelationServiceImpl extends ServiceImpl<CurelationMapper, Curelation>
    implements CurelationService{

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CurelationMapper curelationMapper;

    @Override
    public void listen(Curelation curelation) {
        //需要查询出相关的课程信息
        //封装一个查询条件 先看看 课程有无听课记录
        QueryWrapper<Curelation> curelationQueryWrapper = new QueryWrapper<>();
        curelationQueryWrapper.eq("courseid",curelation.getCourseid());
        Curelation selectOne = baseMapper.selectOne(curelationQueryWrapper);
        if (selectOne != null){
            //若有 更新听课人名称
            String oldName = selectOne.getName();
            if (oldName == null){
                selectOne.setName(curelation.getName());
            }else {
                selectOne.setName(oldName+","+curelation.getName());
            }
            //返回用更新的sql方法
            baseMapper.updateById(selectOne);
            }else {
            curelation.setId(String.valueOf(Math.random()).substring(2,10));
            curelation.setType(courseMapper.selectById(curelation.getCourseid()).getCoursename());
            baseMapper.insert(curelation);
        }
    }

    @Override
    public void delCurelation(String id) {
        curelationMapper.deleteById(id);
    }
}




