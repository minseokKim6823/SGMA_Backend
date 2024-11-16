package com.springcooler.sgma.choice.query.controller;

import com.springcooler.sgma.choice.common.ResponseDTO;
import com.springcooler.sgma.choice.common.exception.CommonException;
import com.springcooler.sgma.choice.common.exception.ErrorCode;
import com.springcooler.sgma.choice.query.dto.ChoiceDTO;
import com.springcooler.sgma.choice.query.service.ChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryChoiceController")
@RequestMapping("/api/problem-choices")
public class ChoiceController {
    private final ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping("/")
    public ResponseDTO<?> getAllChoices() {
        List<ChoiceDTO> choices = choiceService.findAllChoices();
        if (choices == null || choices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_CHOICE);
        }
        return ResponseDTO.ok(choices);
    }

    @GetMapping("/problems/{problemId}")
    public ResponseDTO<?> getChoicesByProblemId(@PathVariable("problemId") long problemId) {
        List<ChoiceDTO> choices = choiceService.findChoicesByProblemId(problemId);
        if (choices == null || choices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_CHOICE);
        }
        return ResponseDTO.ok(choices);
    }
}
