package com.springcooler.sgma.problem.command.infrastructure.service;

import com.springcooler.sgma.choice.command.domain.aggregate.vo.ProblemVO;


import java.util.List;


public interface InfraProblemService {
    ProblemVO requestRegistChoices(long problemId, List<String> choices);

    void requestIncreaseNumSubmittedProblems(long scheduleId, long participantId);

    void requestDecreaseNumSubmittedProblems(long scheduleId, long participantId);
}
