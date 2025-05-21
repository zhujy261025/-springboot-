package com.gec.springbootcourse.controller;

import com.gec.springbootcourse.entity.Curelation;
import com.gec.springbootcourse.service.CurelationService;
import com.gec.springbootcourse.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurelationController {
    @Autowired
    CurelationService curelationService;

    @GetMapping("/queryCurelation")
    public Result queryCurelation(){
        return Result.success(curelationService.list());
    }

    @PostMapping("/listening")
    public Result listening(@RequestBody Curelation curelation){
        System.out.println(curelation);
        //听课业务逻辑代码
        curelationService.listen(curelation);
        return Result.success();
    }

        // 删除听课安排
    @DeleteMapping("/delCurelation/{id}")
    public Result delCurelation(@PathVariable String id){
        curelationService.delCurelation(id);
        return Result.success();
    }
}
