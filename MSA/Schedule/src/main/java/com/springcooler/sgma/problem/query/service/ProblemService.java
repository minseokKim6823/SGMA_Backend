package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.query.dto.ProblemDTO;

import java.util.List;

public interface ProblemService {

    // 문제 전체 조회
    List<ProblemDTO> findAllProblems();

    // ID로 문제 조회
    ProblemDTO findProblemById(Long id);

    // 스케쥴 ID로 문제 조회
    List<ProblemDTO> findProblemsByScheduleId(Long scheduleId);

}
