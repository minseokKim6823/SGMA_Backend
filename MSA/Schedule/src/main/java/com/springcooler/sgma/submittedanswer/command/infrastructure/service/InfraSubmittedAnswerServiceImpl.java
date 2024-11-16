package com.springcooler.sgma.submittedanswer.command.infrastructure.service;

import com.springcooler.sgma.problem.query.dto.ProblemVO;
import com.springcooler.sgma.problem.query.service.ProblemService;
import com.springcooler.sgma.studyscheduleparticipant.command.application.service.AppStudyScheduleParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfraSubmittedAnswerServiceImpl implements InfraSubmittedAnswerService
{

    private final ProblemService problemService;
    private final AppStudyScheduleParticipantService participantService;
    private final ModelMapper modelMapper;
    @Autowired
    public InfraSubmittedAnswerServiceImpl(ProblemService problemService
            , AppStudyScheduleParticipantService participantService, ModelMapper modelMapper) {
        this.problemService = problemService;
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProblemVO requestProblemInfo(Long problemId) {
        ProblemVO problemInfo = modelMapper.map(problemService.findProblemById(problemId), ProblemVO.class);
        return problemInfo;
    }

    @Override
    public void requestUpdateParticipantScore(long scheduleId, long participantId, double score) {
        participantService.gradeSubmittedAnswersByParticipantId(scheduleId, participantId, score);
    }

}