package com.springcooler.sgma.recruitmentboardreply.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("recruitmentBoardReplyMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.recruitmentboardreply.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
