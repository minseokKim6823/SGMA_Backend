package com.springcooler.sgma.studygroupmember.command.domain.repository;

import com.springcooler.sgma.studygroupmember.command.domain.aggregate.StudyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupMemberRepository extends JpaRepository<StudyGroupMember, Long> {
}
