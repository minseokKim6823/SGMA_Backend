package com.springcooler.sgma.studygroupboardlike.command.domain.repository;

import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.StudyGroupBoardLike;
import com.springcooler.sgma.studygroupboardlike.command.domain.aggregate.StudyGroupBoardLikePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupBoardLikeRepository extends JpaRepository<StudyGroupBoardLike, StudyGroupBoardLikePK> {
}
