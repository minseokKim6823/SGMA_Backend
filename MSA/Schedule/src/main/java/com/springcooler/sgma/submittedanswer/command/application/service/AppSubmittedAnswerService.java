package com.springcooler.sgma.submittedanswer.command.application.service;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.command.domain.aggregate.entity.SubmittedAnswer;

import java.util.List;

public interface AppSubmittedAnswerService {
    void registSubmittedAnswer(List<SubmittedAnswerDTO> submittedAnswers);

    SubmittedAnswer modifySubmittedAnswer(SubmittedAnswerDTO modifySubmittedAnswer);

    void gradeSubmittedAnswerByScheduleId(Long scheduleId);
    double gradeSubmittedAnswersByScheduleIdAndParticipantId(Long scheduleId, Long participantId);


}
