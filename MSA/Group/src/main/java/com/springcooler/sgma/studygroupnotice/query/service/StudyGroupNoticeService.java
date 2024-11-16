package com.springcooler.sgma.studygroupnotice.query.service;

import com.springcooler.sgma.studygroupnotice.query.dto.PageDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;

public interface StudyGroupNoticeService {

    // 스터디그룹 공지사항 전체 조회(스터디그룹 아이디)
    PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByGroupId(Long groupId, Integer pageNo);

    // 스터디그룹 공지사항 단건 조회(공지사항 아이디)
    StudyGroupNoticeDTO findStudyGroupNoticeByNoticeId(Long noticeId);

    // 스터디그룹 공지사항 제목으로 조회
    PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByTitle(Long groupId, String title, Integer pageNo);

    // 스터디그룹 공지사항 내용으로 조회
    PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByContent(Long groupId, String content, Integer pageNo);

}
