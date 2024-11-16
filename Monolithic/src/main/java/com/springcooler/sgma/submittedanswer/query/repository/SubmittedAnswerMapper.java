package com.springcooler.sgma.submittedanswer.query.repository;

import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubmittedAnswerMapper {
    List<SubmittedAnswerDTO> findAllSubmittedAnswers();

    List<SubmittedAnswerDTO> findSubmittedAnswersByProblemId(long problemId);

    List<SubmittedAnswerDTO> findSubmittedAnswersByParticipantId(long participantId);

}
