package com.gec.springbootcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.springbootcourse.entity.Notice;
import com.gec.springbootcourse.service.NoticeService;
import com.gec.springbootcourse.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.List;

/**
 *
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> queryNotice() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("creatDate");  // 排序字段
        return noticeMapper.selectList(queryWrapper);  // 使用selectList进行查询
    }

    @Override
    public void saveNotice(Notice notice) {
        //判断公告ID是否为null或空字符串。如果ID不存在就新增数据，否则就修改数据
        if (StringUtils.isEmpty(notice.getId())){
            //生成ID，并赋值给公告ID属性。
            notice.setId(String.valueOf(Math.random()).substring(2, 8));
            //设置公告创建时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            notice.setCreatdate(sdf.format(new Date()));
            noticeMapper.insert(notice);
        }else {
            noticeMapper.updateById(notice);
        }
    }

    @Override
    public void delNotice(String id) {
        noticeMapper.deleteById(id);
    }
}




