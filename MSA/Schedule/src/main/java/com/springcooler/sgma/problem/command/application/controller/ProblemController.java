package com.springcooler.sgma.problem.command.application.controller;

import com.springcooler.sgma.problem.command.application.service.AppProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problems")
@Slf4j
public class ProblemController {
    private final AppProblemService appProblemService;

    @Autowired
    public ProblemController(AppProblemService appProblemService) {
        this.appProblemService = appProblemService;
    }

//    @PostMapping("/")
//    public ResponseDTO<?> registProblemAndChoicess(@RequestBody ProblemAndChoiceDTO newProblemAndChoice) {
//        ProblemAndChoiceDTO problemAndChoice = appProblemService.registProblemAndChoice(newProblemAndChoice);
//        return ResponseDTO.ok(problemAndChoice);
//    }

//    @DeleteMapping("/{problemId}")
//    public ResponseDTO<?> deleteProblem(@PathVariable("problemId") long problemId) {
//        appProblemService.deleteProblem(problemId);
//
//        return ResponseDTO.ok(null);
//    }
}
