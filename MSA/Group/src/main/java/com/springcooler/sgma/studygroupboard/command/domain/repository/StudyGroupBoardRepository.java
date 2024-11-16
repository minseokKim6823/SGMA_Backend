package com.springcooler.sgma.studygroupboard.command.domain.repository;

import com.springcooler.sgma.studygroupboard.command.domain.aggregate.StudyGroupBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupBoardRepository extends JpaRepository<StudyGroupBoard, Long> {
}
