package com.springcooler.sgma.studygroupboardreply.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupBoardReplyMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupboardreply.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
