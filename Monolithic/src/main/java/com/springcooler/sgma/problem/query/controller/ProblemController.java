package com.springcooler.sgma.problem.query.controller;

import com.springcooler.sgma.problem.common.ResponseDTO;
import com.springcooler.sgma.problem.query.common.ResponseMessage;
import com.springcooler.sgma.problem.query.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import com.springcooler.sgma.problem.query.service.ProblemService;
import com.springcooler.sgma.problem.query.service.ProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("queryProblemController")
@RequestMapping("/api/problems")
@Slf4j
public class ProblemController {

    private ProblemService problemService;
    @Autowired
    public ProblemController(ProblemServiceImpl queryProblemService, List<ProblemDTO> problems) {
        this.problemService = queryProblemService;
    }

    // 문제 전체 조회
    @GetMapping("/")
    public ResponseDTO<?> getAllProblems() {
        List<ProblemDTO> problems = problemService.findAllProblems();
        return ResponseDTO.ok(problems);
        }
    // 문제 ID로 문제 조회
    @GetMapping("/{problemId}")
    public ResponseDTO<?> getProblemById(@PathVariable("problemId") long problemId) {
        ProblemAndChoiceDTO problem = problemService.findProblemAndChoiceByProblemId(problemId);
        return ResponseDTO.ok(String.valueOf(problem));
    }

    // 특정 스케쥴에 해당하는 문제 조회
    @GetMapping("schedules/{scheduleId}")
    public ResponseDTO<?> getProblemsByScheduleId(@PathVariable("scheduleId") long scheduleId) {
        List<ProblemDTO> problems = problemService.findProblemsByScheduleId(scheduleId);
        return ResponseDTO.ok(problems);
    }

    // 일정 ID와 참가자 ID로 문제 조회
    @GetMapping("schedules/{scheduleId}/participants/{participantId}")
    public ResponseDTO<?> getProblemsByScheduleIdAndParticipantId(@PathVariable("scheduleId") long scheduleId, @PathVariable("participantId") long participantId) {
        List<ProblemDTO> problems = problemService.findProblemsByScheduleIdAndParticipantId(scheduleId, participantId);
        return ResponseDTO.ok(problems);
    }



}
