package com.springcooler.sgma.problem.query.repository;

import com.springcooler.sgma.problem.query.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProblemMapper {
    List<ProblemDTO> findAllProblems();

    List<ProblemDTO> findProblemsByScheduleId(long scheduleId);

    List<ProblemDTO> findProblemsByScheduleIdAndParticipantId(Map<String, Long> map);

    ProblemDTO findProblemByProblemId(long problemId);

    ProblemAndChoiceDTO findProblemAndChoiceByProblemId(long problemId);
}

