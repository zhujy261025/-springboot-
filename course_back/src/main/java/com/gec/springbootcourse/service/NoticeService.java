package com.gec.springbootcourse.service;

import com.gec.springbootcourse.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface NoticeService extends IService<Notice> {

    List<Notice> queryNotice();

    void saveNotice(Notice notice);

    void delNotice(String id);
}
