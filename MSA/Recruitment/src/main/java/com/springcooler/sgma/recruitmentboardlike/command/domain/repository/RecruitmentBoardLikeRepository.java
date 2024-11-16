package com.springcooler.sgma.recruitmentboardlike.command.domain.repository;

import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RecruitmentBoardLike;
import com.springcooler.sgma.recruitmentboardlike.command.domain.aggregate.RecruitmentBoardLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentBoardLikeRepository extends JpaRepository<RecruitmentBoardLike, RecruitmentBoardLikeId> {
    RecruitmentBoardLike findByRecruitmentBoardIdAndUserId(Long recruitmentBoardId, Long userId);
}
