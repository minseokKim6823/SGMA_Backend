package com.springcooler.sgma.studygroupnotice.query.service;

import com.springcooler.sgma.studygroupnotice.common.exception.CommonException;
import com.springcooler.sgma.studygroupnotice.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupnotice.query.dto.PageDTO;
import com.springcooler.sgma.studygroupnotice.query.dto.StudyGroupNoticeDTO;
import com.springcooler.sgma.studygroupnotice.query.repository.StudyGroupNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupNoticeServiceImpl implements StudyGroupNoticeService {

    private final Integer PAGE_SIZE = 10;
    private final Integer ELEMENTS_PER_PAGE = 10;

    private final StudyGroupNoticeMapper studyGroupNoticeMapper;

    @Autowired
    public StudyGroupNoticeServiceImpl(StudyGroupNoticeMapper studyGroupNoticeMapper) {
        this.studyGroupNoticeMapper = studyGroupNoticeMapper;
    }

    // 스터디그룹 공지사항 전체 조회(스터디그룹 아이디)
    @Override
    public PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByGroupId(Long groupId, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 그룹별 전체 공지사항 개수 조회
        Integer totalElements = studyGroupNoticeMapper.getTotalElementsByGroupId(groupId);
        if(totalElements == null || totalElements < 1) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }

        // 현재 페이지 공지사항 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupNoticeDTO> notices =
                studyGroupNoticeMapper.findStudyGroupNoticesByGroupId(groupId, ELEMENTS_PER_PAGE, offset);
        if (notices == null || notices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }

        return new PageDTO<>(notices, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
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

    // 스터디그룹 공지사항 제목으로 조회
    @Override
    public PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByTitle(Long groupId, String title, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 제목별 전체 공지사항 개수 조회
        Integer totalElements = studyGroupNoticeMapper.getTotalElementsByTitle(groupId, title);

        // 현재 페이지 공지사항 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupNoticeDTO> notices =
                studyGroupNoticeMapper.findStudyGroupNoticesByTitle(groupId, title, ELEMENTS_PER_PAGE, offset);
        if (notices == null || notices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }

        return new PageDTO<>(notices, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

    // 스터디그룹 공지사항 내용으로 조회
    @Override
    public PageDTO<StudyGroupNoticeDTO> findStudyGroupNoticesByContent(Long groupId, String content, Integer pageNo) {
        // 페이지 번호 유효성 검사
        if(pageNo == null || pageNo < 1) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER_FORMAT);
        }

        // 내용별 전체 공지사항 개수 조회
        Integer totalElements = studyGroupNoticeMapper.getTotalElementsByContent(groupId, content);

        // 현재 페이지 공지사항 조회
        Integer offset = (pageNo - 1) * ELEMENTS_PER_PAGE;
        List<StudyGroupNoticeDTO> notices =
                studyGroupNoticeMapper.findStudyGroupNoticesByContent(groupId, content, ELEMENTS_PER_PAGE, offset);
        if (notices == null || notices.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_NOTICE);
        }

        return new PageDTO<>(notices, pageNo, PAGE_SIZE, ELEMENTS_PER_PAGE, totalElements);
    }

}
