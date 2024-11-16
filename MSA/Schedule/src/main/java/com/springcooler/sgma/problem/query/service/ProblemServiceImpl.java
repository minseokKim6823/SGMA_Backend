package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.common.exception.ErrorCode;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import com.springcooler.sgma.problem.query.repository.ProblemMapper;
import com.springcooler.sgma.problem.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public ProblemDTO findProblemById(Long id) {
        ProblemDTO problem = problemMapper.findProblemById(id);
        if(problem == null || problem.getProblemId() == 0) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problem;
    }

    @Override
    public List<ProblemDTO> findProblemsByScheduleId(Long scheduleId) {
        List<ProblemDTO> problems = problemMapper.findProblemsByScheduleId(scheduleId);

        if(problems == null || problems.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_PROBLEM);
        }
        return problems;
    }


}
