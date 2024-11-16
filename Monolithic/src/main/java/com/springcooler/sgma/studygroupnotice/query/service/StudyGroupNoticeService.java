package com.springcooler.sgma.studygroupnotice.query.service;

import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;

import java.util.List;

public interface StudyGroupNoticeService {

    // 스터디그룹 공지사항 전체 조회(스터디그룹 아이디)
    List<StudyGroupNoticeDTO> findStudyGroupNoticesByGroupId(Long groupId);

    // 스터디그룹 공지사항 단건 조회(공지사항 아이디)
    StudyGroupNoticeDTO findStudyGroupNoticeByNoticeId(Long noticeId);

}
