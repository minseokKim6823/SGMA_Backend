package com.springcooler.sgma.recruitmentboardreply.command.domain.repository;

import com.springcooler.sgma.recruitmentboardreply.command.domain.aggregate.RecruitmentBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentBoardReplyRepository extends JpaRepository<RecruitmentBoardReply,Long> {
}
