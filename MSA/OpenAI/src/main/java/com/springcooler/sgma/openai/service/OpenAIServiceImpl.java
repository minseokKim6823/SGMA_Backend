package com.springcooler.sgma.openai.service;

import com.springcooler.sgma.openai.dto.RequestChatDTO;
import com.springcooler.sgma.openai.dto.ResponseChatDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    private final RestTemplate template;

    @Autowired
    public OpenAIServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ResponseChatDTO getGrade(String prompt) {
        RequestChatDTO req = new RequestChatDTO(model, prompt);
        return template.postForObject(url, req, ResponseChatDTO.class);
    }

}
