package com.springcooler.sgma.problem.query.controller;


import com.springcooler.sgma.problem.common.ResponseDTO;
import com.springcooler.sgma.problem.query.service.ProblemService;
import com.springcooler.sgma.problem.query.service.ProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("queryProblemController")
@RequestMapping("/api/study-problems")
@Slf4j
public class ProblemController {

    private ProblemService problemService;
    @Autowired
    public ProblemController(ProblemServiceImpl queryProblemService) {
        this.problemService = queryProblemService;
    }

    @GetMapping("/")
    public ResponseDTO<?> getAllProblems(){
        return ResponseDTO.ok(problemService.findAllProblems());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseDTO<?> getProblemsByScheduleId(@PathVariable("scheduleId") Long scheduleId){
        return ResponseDTO.ok(problemService.findProblemsByScheduleId(scheduleId));
    }


}
