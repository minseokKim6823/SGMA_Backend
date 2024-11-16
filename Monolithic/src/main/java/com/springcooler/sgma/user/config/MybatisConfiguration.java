package com.springcooler.sgma.user.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.springcooler.sgma.user",annotationClass = Mapper.class)
public class MybatisConfiguration {
}
