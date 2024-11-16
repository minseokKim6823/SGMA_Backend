package com.springcooler.sgma.choice.command.application.controller;

import com.springcooler.sgma.choice.command.application.service.AppChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandChoiceController")
@RequestMapping("/api/problem-choices")
public class ChoiceController {
    private final AppChoiceService appChoiceService;

    @Autowired
    public ChoiceController(AppChoiceService appChoiceService) {
        this.appChoiceService = appChoiceService;
    }

}
