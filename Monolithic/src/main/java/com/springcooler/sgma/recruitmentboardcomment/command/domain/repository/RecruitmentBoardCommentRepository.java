package com.springcooler.sgma.recruitmentboardcomment.command.domain.repository;

import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.RecruitmentBoardComment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentBoardCommentRepository extends JpaRepository<RecruitmentBoardComment,Long> {
}
