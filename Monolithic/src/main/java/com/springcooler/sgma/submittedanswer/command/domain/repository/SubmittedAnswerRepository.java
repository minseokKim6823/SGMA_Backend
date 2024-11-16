package com.springcooler.sgma.submittedanswer.command.domain.repository;

import com.springcooler.sgma.submittedanswer.command.application.dto.SubmittedAnswerDTO;
import com.springcooler.sgma.submittedanswer.command.domain.aggregate.SubmittedAnswer;
import com.springcooler.sgma.submittedanswer.command.domain.aggregate.SubmittedAnswerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswer, SubmittedAnswerPK> {
List<SubmittedAnswer> findByParticipantId(Long participantId);
}
