package com.springcooler.sgma.studygroupboardcomment.command.domain.repository;

import com.springcooler.sgma.studygroupboardcomment.command.domain.aggregate.StudyGroupBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupBoardCommentRepository extends JpaRepository<StudyGroupBoardComment, Long> {
}
