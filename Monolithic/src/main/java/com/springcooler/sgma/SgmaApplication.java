package com.springcooler.sgma;

import com.springcooler.sgma.recruitmentboard.query.service.RecruitmentBoardService;
import com.springcooler.sgma.recruitmentboardreply.query.service.RecruitmentBoardReplyService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SgmaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SgmaApplication.class, args);
		RecruitmentBoardReplyService recruitmentBoardReplyService =new RecruitmentBoardReplyService();
		recruitmentBoardReplyService.getAllRecruitmentBoardReply();

	}
}
