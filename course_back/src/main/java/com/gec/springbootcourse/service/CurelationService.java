package com.gec.springbootcourse.service;

import com.gec.springbootcourse.entity.Curelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface CurelationService extends IService<Curelation> {

    void listen(Curelation curelation);

    void delCurelation(String id);
}
