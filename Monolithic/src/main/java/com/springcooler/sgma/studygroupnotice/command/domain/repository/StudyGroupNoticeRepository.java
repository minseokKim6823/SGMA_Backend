package com.springcooler.sgma.studygroupnotice.command.domain.repository;

import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.StudyGroupNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupNoticeRepository extends JpaRepository<StudyGroupNotice, Long> {
}
