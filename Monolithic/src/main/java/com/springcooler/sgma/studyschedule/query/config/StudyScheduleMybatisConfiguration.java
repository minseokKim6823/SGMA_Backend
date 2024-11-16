package com.springcooler.sgma.studyschedule.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.springcooler.sgma.studyschedule.query.repository", annotationClass= Mapper.class)
public class StudyScheduleMybatisConfiguration {
}
