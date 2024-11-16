package com.springcooler.sgma.studygroup.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroup.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
