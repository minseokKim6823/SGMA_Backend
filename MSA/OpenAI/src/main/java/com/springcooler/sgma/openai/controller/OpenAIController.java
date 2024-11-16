package com.springcooler.sgma.openai.controller;

import com.springcooler.sgma.openai.dto.ResponseChatDTO;
import com.springcooler.sgma.openai.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bot")
public class OpenAIController {

    private final OpenAIService openAIService;

    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/grade")
    public String chat(@RequestParam(name = "problem") String problem,
                       @RequestParam(name = "answer") String answer,
                       @RequestParam(name = "submitted-answer") String submittedAnswer) {
        String prompt = "\"" + problem + "\"라는 문제와 \"" + answer + "\"라는 정답이 있을 때" +
                "\"" + submittedAnswer + "\"가 의미상 또는 수치상 정답이면 'O', 아니면 'X'로 간단한 설명과 함께 판정해줘"
                + ",단, 대답은 판정 결과 1글자와 설명 1줄을 ','로 구별해서 줄바꿈 없이 대답해줘";
        ResponseChatDTO res = openAIService.getGrade(prompt);
        return res.getChoices().get(0).getMessage().getContent();
    }

}
