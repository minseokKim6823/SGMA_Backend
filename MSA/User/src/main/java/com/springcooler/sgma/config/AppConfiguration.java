package com.springcooler.sgma.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /* 설명. RestTemplate 빈 추가 */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
