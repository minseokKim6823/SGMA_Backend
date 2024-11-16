package com.springcooler.sgma.recruitmentboard.batch;

import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.BoardActiveStatus;
import com.springcooler.sgma.recruitmentboard.command.domain.aggregate.RecruitmentBoard;
import com.springcooler.sgma.recruitmentboard.command.domain.repository.RecruitmentBoardRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class RecruitmentBoardUpdateTasklet implements Tasklet {
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    public RecruitmentBoardUpdateTasklet(RecruitmentBoardRepository recruitmentBoardRepository) {
        this.recruitmentBoardRepository = recruitmentBoardRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        ZonedDateTime nowKst = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        Timestamp currentTime  = Timestamp.from(nowKst.toInstant());
        List<RecruitmentBoard> recruitmentBoardList  =recruitmentBoardRepository.findByActiveStatus(BoardActiveStatus.ACTIVE);

        for(RecruitmentBoard recruitmentBoard: recruitmentBoardList){
            recruitmentBoard.checkAndUpdateStatus(currentTime);
            recruitmentBoardRepository.save(recruitmentBoard);
        }

        return RepeatStatus.FINISHED;
    }
}
