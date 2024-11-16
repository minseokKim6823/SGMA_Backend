package com.springcooler.sgma.submittedanswer.query.config;

import com.springcooler.sgma.submittedanswer.query.repository.SubmittedAnswerMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.springcooler.sgma.submittedanswer.query.repository", annotationClass = Mapper.class)
@Configuration("submittedAnswerMybatisConfiguration")
public class MybatisConfiguration
{
    private SubmittedAnswerMapper submittedAnswerMapper;
}
