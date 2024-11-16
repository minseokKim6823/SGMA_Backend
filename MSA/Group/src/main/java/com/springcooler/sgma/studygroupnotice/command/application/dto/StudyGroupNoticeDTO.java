package com.springcooler.sgma.studygroupnotice.command.application.dto;

import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.StudyGroupNoticeStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudyGroupNoticeDTO {
    private Long noticeId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StudyGroupNoticeStatus activeStatus;
    private Long groupId;
}
