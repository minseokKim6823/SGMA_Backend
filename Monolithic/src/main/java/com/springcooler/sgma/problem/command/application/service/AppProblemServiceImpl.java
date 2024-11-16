package com.springcooler.sgma.problem.command.application.service;

import com.springcooler.sgma.choice.command.domain.aggregate.vo.ProblemVO;
import com.springcooler.sgma.problem.command.application.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.command.application.dto.ProblemDTO;
import com.springcooler.sgma.problem.command.domain.aggregate.entity.Problem;
import com.springcooler.sgma.problem.command.domain.repository.ProblemRepository;
import com.springcooler.sgma.studyscheduleparticipant.command.infrastructure.service.InfraStudyScheduleParticipantService;
import com.springcooler.sgma.problem.command.infrastructure.service.InfraProblemService;
import com.springcooler.sgma.problem.common.exception.CommonException;
import com.springcooler.sgma.problem.common.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AppProblemServiceImpl implements AppProblemService {

    private final ModelMapper modelMapper;
    private final ProblemRepository problemRepository;
    private final InfraProblemService infraProblemService;

    @Autowired
    public AppProblemServiceImpl(ModelMapper modelMapper, ProblemRepository problemRepository, InfraProblemService infraProblemService) {
        this.modelMapper = modelMapper;
        this.problemRepository = problemRepository;
        this.infraProblemService = infraProblemService;
    }
//
//    @Transactional
//    @Override
//    public Problem registProblem(ProblemDTO newProblem) {
//        try {
//            Problem problem = modelMapper.map(newProblem, Problem.class);
//            problem = problemRepository.save(problem);
//            return problem;
//
//        } catch (Exception e) {
//            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @Transactional
//    @Override
//    public void modifyProblem(ProblemDTO modifiedProblem) {
//        Problem existingProblem = problemRepository.findById(modifiedProblem.getProblemId()).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PROBLEM));
//
//        problemRepository.save(modelMapper.map(modifiedProblem, existingProblem));
//
//    }

    @Transactional
    @Override
    public void deleteProblem(long problemId) {
        Problem deleteProblem = problemRepository.findById(problemId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PROBLEM));
        infraProblemService.requestDecreaseNumSubmittedProblems(deleteProblem.getScheduleId(), deleteProblem.getParticipantId());
        problemRepository.delete(deleteProblem);
    }


    @Transactional
    @Override
    public ProblemAndChoiceDTO registProblemAndChoice(ProblemAndChoiceDTO newProblemAndChoice) {
        Problem problem = new Problem(null,
                newProblemAndChoice.getContent(),
                newProblemAndChoice.getAnswer(),
                newProblemAndChoice.getParticipantId(),
                newProblemAndChoice.getScheduleId());

        try {
            Problem registeredProblem = problemRepository.save(problem);
            ProblemVO problemVO = infraProblemService.requestRegistChoices(registeredProblem.getProblemId(), newProblemAndChoice.getChoices());
            infraProblemService.requestIncreaseNumSubmittedProblems(problem.getScheduleId(), problem.getParticipantId());
            return new ProblemAndChoiceDTO(registeredProblem.getProblemId(), registeredProblem.getParticipantId(), registeredProblem.getScheduleId(), registeredProblem.getContent(), registeredProblem.getAnswer(), problemVO.getChoices());

        } catch (Exception e) {
            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}