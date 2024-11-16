package com.springcooler.sgma.studygroup.command.domain.repository;

import com.springcooler.sgma.studygroup.command.domain.aggregate.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
}
