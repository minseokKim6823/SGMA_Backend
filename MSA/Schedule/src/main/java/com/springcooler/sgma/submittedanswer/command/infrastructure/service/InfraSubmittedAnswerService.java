package com.springcooler.sgma.submittedanswer.command.infrastructure.service;


import com.springcooler.sgma.problem.query.dto.ProblemVO;

public interface InfraSubmittedAnswerService {
    ProblemVO requestProblemInfo(Long problemId);
    void requestUpdateParticipantScore(long scheduleId, long participantId, double score);

}
