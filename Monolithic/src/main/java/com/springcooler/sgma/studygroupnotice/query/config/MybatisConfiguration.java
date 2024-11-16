package com.springcooler.sgma.studygroupnotice.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupNoticeMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupnotice.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
