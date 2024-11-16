package com.springcooler.sgma.studygroupapplicant.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupApplicantMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupapplicant.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
