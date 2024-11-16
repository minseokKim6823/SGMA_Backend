package com.springcooler.sgma.problem.command.application.service;

import com.springcooler.sgma.problem.command.application.dto.ProblemDTO;
import com.springcooler.sgma.problem.command.domain.aggregate.entity.Problem;
import com.springcooler.sgma.problem.command.domain.aggregate.entity.ProblemType;
import com.springcooler.sgma.problem.command.domain.aggregate.entity.ProblemVO;
import com.springcooler.sgma.problem.command.domain.repository.ProblemRepository;
import com.springcooler.sgma.problem.command.infrastructure.service.InfraProblemService;
import com.springcooler.sgma.problem.common.exception.CommonException;
import com.springcooler.sgma.problem.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AppProblemServiceImpl implements AppProblemService {

    private final ProblemRepository problemRepository;
    private final InfraProblemService infraProblemService;

    @Autowired
    public AppProblemServiceImpl(ProblemRepository problemRepository, InfraProblemService infraProblemService) {
        this.problemRepository = problemRepository;
        this.infraProblemService = infraProblemService;
    }


    @Transactional
    @Override
    public void deleteProblem(Long problemId) {
        Problem deleteProblem = problemRepository.findById(problemId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PROBLEM));
        infraProblemService.requestDecreaseNumSubmittedProblems(deleteProblem.getScheduleId(), deleteProblem.getParticipantId());
        problemRepository.delete(deleteProblem);
    }

    @Transactional
    @Override
    public ProblemDTO registProblem(ProblemDTO problemInfo) {
        Problem newProblem = new Problem(null
                , problemInfo.getContent()
                , problemInfo.getAnswer()
                , ProblemType.MULTIPLE
                , problemInfo.getParticipantId()
                , problemInfo.getScheduleId());

        // TODO: String, Enum Converter로 교체
        if(problemInfo.getProblemType().equals("ESSAY"))
            newProblem.setProblemType(ProblemType.ESSAY);

        problemRepository.save(newProblem);
        if (newProblem.getProblemType().equals(ProblemType.MULTIPLE)){
        ProblemVO problemvo = infraProblemService.requestRegistChoices(newProblem.getProblemId(),problemInfo.getChoices());
            problemInfo.setChoices(problemvo.getChoices());
        }
        problemInfo.setProblemId(newProblem.getProblemId());
        return problemInfo;
    }
}