package com.springcooler.sgma.recruitmentboardcomment.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("recruitmentBoardCommentMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.recruitmentboardcomment.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
