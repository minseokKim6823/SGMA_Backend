package com.springcooler.sgma.studygroupboardreply.command.domain.repository;

import com.springcooler.sgma.studygroupboardreply.command.domain.aggregate.StudyGroupBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupBoardReplyRepository extends JpaRepository<StudyGroupBoardReply, Long> {
}
