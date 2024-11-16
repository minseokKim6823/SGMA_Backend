package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.common.exception.ErrorCode;
import com.springcooler.sgma.problem.query.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import com.springcooler.sgma.problem.query.repository.ProblemMapper;
import com.springcooler.sgma.problem.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class ProblemServiceImpl implements ProblemService {
    private ProblemMapper problemMapper;

    @Autowired
    public ProblemServiceImpl(ProblemMapper problemMapper) {
        this.problemMapper = problemMapper;
    }

    public List<ProblemDTO> findAllProblems() {
        List<ProblemDTO> problems = problemMapper.findAllProblems();
        if(problems == null || problems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problems;
    }

    public List<ProblemDTO> findProblemsByScheduleId(long scheduleId) {
        List<ProblemDTO> problems = problemMapper.findProblemsByScheduleId(scheduleId);
        if(problems == null || problems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problems;
    }

    public List<ProblemDTO> findProblemsByScheduleIdAndParticipantId(long scheduleId, long participantId) {

        Map<String, Long> map = new HashMap<>();
        map.put("scheduleId", scheduleId);
        map.put("participantId",participantId);
        List<ProblemDTO> problems = problemMapper.findProblemsByScheduleIdAndParticipantId(map);
        if (problems == null || problems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problems;
    }

    @Override
    public ProblemDTO findProblemByProblemId(long problemId) {
        ProblemDTO problem = problemMapper.findProblemByProblemId(problemId);

        if(problem == null || problem.getProblemId()==0) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problem;
    }

    @Override
    public ProblemAndChoiceDTO findProblemAndChoiceByProblemId(long problemId) {
        ProblemAndChoiceDTO problemAndChoiceDTO = problemMapper.findProblemAndChoiceByProblemId(problemId);
        log.info("problemAndChoiceDTO: {}", problemAndChoiceDTO);
        return problemAndChoiceDTO;
    }
}
