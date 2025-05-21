package com.gec.springbootcourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.gec.springbootcourse.mapper")
@SpringBootApplication
public class SpringbootCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCourseApplication.class, args);
    }

}
