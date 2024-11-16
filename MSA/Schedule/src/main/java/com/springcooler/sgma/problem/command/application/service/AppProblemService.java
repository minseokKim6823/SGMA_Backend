package com.springcooler.sgma.problem.command.application.service;


import com.springcooler.sgma.problem.command.application.dto.ProblemDTO;

public interface AppProblemService {

    void deleteProblem(Long problemId);

    ProblemDTO registProblem(ProblemDTO newProblem);

}
