package com.springcooler.sgma.choice.query.config;

import com.springcooler.sgma.choice.query.repository.ChoiceMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@MapperScan(basePackages = "com.springcooler.sgma.choice.query.repository", annotationClass = Mapper.class)
@Configuration("choiceMybatisConfiguration")
public class MybatisConfiguration {

    private ChoiceMapper choiceMapper;
}