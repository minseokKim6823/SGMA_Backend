package com.springcooler.sgma.problem.command.infrastructure.service;

import com.springcooler.sgma.choice.command.application.service.AppChoiceService;
import com.springcooler.sgma.problem.command.domain.aggregate.entity.ProblemVO;
import com.springcooler.sgma.studyscheduleparticipant.command.application.service.AppStudyScheduleParticipantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InfraProblemServiceImpl implements InfraProblemService {

    private final AppChoiceService appChoiceService;
    private final AppStudyScheduleParticipantService studyScheduleParticipantService;

    @Autowired
    public InfraProblemServiceImpl(AppChoiceService appChoiceService
    , AppStudyScheduleParticipantService studyScheduleParticipantService) {
        this.appChoiceService = appChoiceService;
        this.studyScheduleParticipantService = studyScheduleParticipantService;
    }

    @Transactional
    @Override
    public ProblemVO requestRegistChoices(Long problemId, List<String> choices) {
        ProblemVO problemVO = new ProblemVO(problemId, choices);
        return appChoiceService.registChoices(problemVO);
    }


    @Override
    public void requestIncreaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        studyScheduleParticipantService.increaseNumSubmittedProblems(scheduleId, participantId);
    }


    @Override
    public void requestDecreaseNumSubmittedProblems(Long scheduleId, Long participantId) {
        studyScheduleParticipantService.decreaseNumSubmittedProblems(scheduleId, participantId);
    }
}
