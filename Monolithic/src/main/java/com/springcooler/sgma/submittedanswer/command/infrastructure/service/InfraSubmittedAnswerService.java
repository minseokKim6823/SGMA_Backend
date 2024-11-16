package com.springcooler.sgma.submittedanswer.command.infrastructure.service;

import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;

import java.util.List;

public interface InfraSubmittedAnswerService {
    int getAnswerByProblemId(long problemId);

    void requestUpdateParticipantScore(long scheduleId, long participantId, double score);
}
