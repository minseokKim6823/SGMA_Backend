package com.springcooler.sgma.studygroupnotice.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="STUDY_GROUP_NOTICE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyGroupNotice {

    @Id
    @Column(name="NOTICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name="UPDATED_AT")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVE_STATUS")
    private StudyGroupNoticeStatus activeStatus;

    @Column(name="GROUP_ID")
    private Long groupId;

}
