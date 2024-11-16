package com.springcooler.sgma.problem.command.infrastructure.service;


import com.springcooler.sgma.problem.command.domain.aggregate.entity.ProblemVO;

import java.util.List;


public interface InfraProblemService {
    ProblemVO requestRegistChoices(Long problemId, List<String> choices);

    void requestIncreaseNumSubmittedProblems(Long scheduleId, Long participantId);

    void requestDecreaseNumSubmittedProblems(Long scheduleId, Long participantId);
}
