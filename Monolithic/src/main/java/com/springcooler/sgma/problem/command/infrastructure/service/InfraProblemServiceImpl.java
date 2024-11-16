package com.springcooler.sgma.problem.command.infrastructure.service;

import com.springcooler.sgma.choice.command.application.service.AppChoiceService;
import com.springcooler.sgma.choice.command.domain.aggregate.vo.ProblemVO;
import com.springcooler.sgma.problem.command.application.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.command.domain.aggregate.vo.ScheduleParticipantVO;
import com.springcooler.sgma.problem.command.domain.aggregate.vo.ScheduleVO;
import com.springcooler.sgma.studyschedule.query.dto.StudyScheduleDTO;
import com.springcooler.sgma.studyschedule.query.service.StudyScheduleService;
import com.springcooler.sgma.studyscheduleparticipant.command.application.service.AppStudyScheduleParticipantService;
import com.springcooler.sgma.studyscheduleparticipant.query.dto.StudyScheduleParticipantDTO;
import com.springcooler.sgma.studyscheduleparticipant.query.service.StudyScheduleParticipantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ProblemVO requestRegistChoices(long problemId, List<String> choices) {
        return appChoiceService.registChoices(new ProblemVO(problemId, choices));
    }


    @Override
    public void requestIncreaseNumSubmittedProblems(long scheduleId, long participantId) {
        studyScheduleParticipantService.increaseNumSubmittedProblems(scheduleId, participantId);
    }


    @Override
    public void requestDecreaseNumSubmittedProblems(long scheduleId, long participantId) {
        studyScheduleParticipantService.decreaseNumSubmittedProblems(scheduleId, participantId);
    }
}
