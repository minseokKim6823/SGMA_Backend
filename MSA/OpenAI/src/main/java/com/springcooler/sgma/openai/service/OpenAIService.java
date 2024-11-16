package com.springcooler.sgma.openai.service;

import com.springcooler.sgma.openai.dto.ResponseChatDTO;

public interface OpenAIService {

    ResponseChatDTO getGrade(String prompt);

}
