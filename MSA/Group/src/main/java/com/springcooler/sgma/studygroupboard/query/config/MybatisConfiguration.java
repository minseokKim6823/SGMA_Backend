package com.springcooler.sgma.studygroupboard.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupBoardMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupboard.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
