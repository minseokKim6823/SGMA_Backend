package com.springcooler.sgma.submittedanswer.command.infrastructure.service;

import com.springcooler.sgma.problem.command.application.service.AppProblemService;
import com.springcooler.sgma.problem.query.service.ProblemService;
import com.springcooler.sgma.studyscheduleparticipant.command.application.service.AppStudyScheduleParticipantService;
import com.springcooler.sgma.submittedanswer.command.application.service.AppSubmittedAnswerService;
import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfraSubmittedAnswerServiceImpl implements InfraSubmittedAnswerService
{

    private final ProblemService problemService;
    private final AppStudyScheduleParticipantService participantService;
    @Autowired
    public InfraSubmittedAnswerServiceImpl(ProblemService problemService
    , AppStudyScheduleParticipantService participantService) {
        this.problemService = problemService;
        this.participantService = participantService;
    }


    @Override
    public int getAnswerByProblemId(long problemId) {
        return problemService.findProblemByProblemId(problemId).getAnswer();
    }

    @Override
    public void requestUpdateParticipantScore(long scheduleId, long participantId, double score) {
        participantService.gradeSubmittedAnswersByParticipantId(scheduleId, participantId, score);
    }
}
