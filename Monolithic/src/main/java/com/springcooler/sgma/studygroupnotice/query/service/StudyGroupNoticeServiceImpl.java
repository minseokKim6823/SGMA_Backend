package com.springcooler.sgma.studygroupnotice.query.service;

import com.springcooler.sgma.studygroupnotice.common.exception.CommonException;
import com.springcooler.sgma.studygroupnotice.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.query.repository.StudyGroupNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupNoticeServiceImpl implements StudyGroupNoticeService {

    private final StudyGroupNoticeMapper studyGroupNoticeMapper;

    @Autowired
    public StudyGroupNoticeServiceImpl(StudyGroupNoticeMapper studyGroupNoticeMapper) {
        this.studyGroupNoticeMapper = studyGroupNoticeMapper;
    }

    // 스터디그룹 공지사항 전체 조회(스터디그룹 아이디)
    @Override
    public List<StudyGroupNoticeDTO> findStudyGroupNoticesByGroupId(Long groupId) {
        List<StudyGroupNoticeDTO> notices = studyGroupNoticeMapper.findStudyGroupNoticesByGroupId(groupId);
        if (notices == null || notices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }
        return notices;
    }

    // 스터디그룹 공지사항 단건 조회(공지사항 아이디)
    @Override
    public StudyGroupNoticeDTO findStudyGroupNoticeByNoticeId(Long noticeId) {
        StudyGroupNoticeDTO notice = studyGroupNoticeMapper.findStudyGroupNoticeByNoticeId(noticeId);
        if (notice == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }
        return notice;
    }

}
