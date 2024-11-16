package com.springcooler.sgma.problem.query.config;

import com.springcooler.sgma.problem.query.repository.ProblemMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;


@MapperScan(basePackages = "com.springcooler.sgma.problem.query.repository", annotationClass = Mapper.class)
@Configuration("problemMybatisConfiguration")
public class MybatisConfiguration {

    private ProblemMapper problemMapper;
}