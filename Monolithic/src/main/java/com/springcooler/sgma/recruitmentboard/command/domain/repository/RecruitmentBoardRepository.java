package com.springcooler.sgma.recruitmentboard.command.domain.repository;

import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.BoardActiveStatus;
import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;
import com.springcooler.sgma.recruitmentboardcomment.command.domain.aggregate.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentBoardRepository extends JpaRepository<RecruitmentBoard,Long> {

    List<RecruitmentBoard> findByActiveStatus(BoardActiveStatus status);
}
