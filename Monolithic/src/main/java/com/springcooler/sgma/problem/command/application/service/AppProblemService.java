package com.springcooler.sgma.problem.command.application.service;

import com.springcooler.sgma.problem.command.application.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.command.application.dto.ProblemDTO;


public interface AppProblemService {
//    ProblemAndChoiceDTO registProblem(ProblemDTO newProblem);

//    ProblemAndChoiceDTO modifyProblem(ProblemDTO modifiedProblem);

    void deleteProblem(long problemId);


    ProblemAndChoiceDTO registProblemAndChoice(ProblemAndChoiceDTO newProblemAndChoice);
}
