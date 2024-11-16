package com.springcooler.sgma.studygroupboardcomment.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupBoardCommentMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupboardcomment.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
