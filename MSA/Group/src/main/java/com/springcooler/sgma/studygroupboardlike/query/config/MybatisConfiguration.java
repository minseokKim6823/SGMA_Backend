package com.springcooler.sgma.studygroupboardlike.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupBoardLikeMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupboardlike.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
