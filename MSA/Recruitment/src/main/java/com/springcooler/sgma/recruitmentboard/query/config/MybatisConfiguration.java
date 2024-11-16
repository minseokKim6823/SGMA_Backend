package com.springcooler.sgma.recruitmentboard.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("recruitmentBoardMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.recruitmentboard.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
