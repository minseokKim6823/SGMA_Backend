package com.springcooler.sgma.recruitmentboard.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job RecruitmentBoardUpdateTasklet;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncher, Job RecruitmentBoardUpdateTasklet) {
        this.jobLauncher = jobLauncher;
        this.RecruitmentBoardUpdateTasklet = RecruitmentBoardUpdateTasklet;
    }

    @Scheduled(fixedRate = 30000) // 30초 마다 실행
    public void runBatchJob() {
        try {
            jobLauncher.run(RecruitmentBoardUpdateTasklet, new JobParametersBuilder().toJobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
