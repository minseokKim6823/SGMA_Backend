package com.springcooler.sgma.submittedanswer.query.service;

import com.springcooler.sgma.submittedanswer.query.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.query.repository.SubmittedAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmittedAnswerServiceImpl implements SubmittedAnswerService{
    private SubmittedAnswerMapper submittedAnswerMapper;

    @Autowired
    public SubmittedAnswerServiceImpl(SubmittedAnswerMapper submittedAnswerMapper) {
        this.submittedAnswerMapper = submittedAnswerMapper;
    }

    @Override
    public List<SubmittedAnswerDTO> findAllSubmittedAnswers() {
        return submittedAnswerMapper.findAllSubmittedAnswers();
    }

    @Override
    public List<SubmittedAnswerDTO> findSubmittedAnswersByProblemId(long problemId) {
        return submittedAnswerMapper.findSubmittedAnswersByProblemId(problemId);
    }

    @Override
    public List<SubmittedAnswerDTO> getSubmittedAnswersByParticipantId(long participantId) {
        return submittedAnswerMapper.findSubmittedAnswersByParticipantId(participantId);
    }
}
