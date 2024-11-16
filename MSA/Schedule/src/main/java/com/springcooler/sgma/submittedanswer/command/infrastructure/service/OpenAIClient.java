package com.springcooler.sgma.submittedanswer.command.infrastructure.service;

import com.springcooler.sgma.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="sgma-ai-service", url="localhost:8080", configuration = FeignClientConfig.class )
public interface OpenAIClient {
    @GetMapping("/ai-service/api/bot/grade")
    String chat(@RequestParam("problem") String problem, @RequestParam("answer") String answer,@RequestParam("submitted-answer") String submittedAnswer);

}
