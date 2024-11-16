package com.springcooler.sgma.problem.query.service;

import com.springcooler.sgma.problem.query.dto.ProblemAndChoiceDTO;
import com.springcooler.sgma.problem.query.dto.ProblemDTO;

import java.util.List;

public interface ProblemService {

    // 문제 전체 조회
    public List<ProblemDTO> findAllProblems();

    // 스케쥴 ID로 문제 조회
    public List<ProblemDTO> findProblemsByScheduleId(long scheduleId);

    // 스케쥴 ID와 참가자 ID로 문제 조회
    public List<ProblemDTO> findProblemsByScheduleIdAndParticipantId(long scheduleId, long participantId);

    ProblemDTO findProblemByProblemId(long problemId);

    // 문제 ID로 문제와 선지 정보 조회(정답은 일부러 주석처리함)
    ProblemAndChoiceDTO findProblemAndChoiceByProblemId(long problemId);
}
