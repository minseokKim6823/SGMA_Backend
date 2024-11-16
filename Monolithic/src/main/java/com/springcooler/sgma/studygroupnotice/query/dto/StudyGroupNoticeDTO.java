package com.springcooler.sgma.studygroupnotice.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcooler.sgma.studygroupnotice.command.domain.aggregate.StudyGroupNoticeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupNoticeDTO {

    @JsonProperty("notice_id")
    private Long noticeId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty("active_status")
    private StudyGroupNoticeStatus activeStatus;

    @JsonProperty("group_id")
    private Long groupId;

}
