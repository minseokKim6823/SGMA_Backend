package com.springcooler.sgma.studygroupcategory.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("studyGroupCategoryMybatisConfiguration")
@MapperScan(basePackages = "com.springcooler.sgma.studygroupcategory.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
