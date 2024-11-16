package com.springcooler.sgma.recruitmentboardlike.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("recruitmentBoardLikeMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.recruitmentboardlike.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
