package com.springcooler.sgma.studygroupboardreply.query.service;

import com.springcooler.sgma.studygroupboardreply.common.exception.CommonException;
import com.springcooler.sgma.studygroupboardreply.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupboardreply.query.dto.StudyGroupBoardReplyDTO;
import com.springcooler.sgma.studygroupboardreply.query.repository.StudyGroupBoardReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupBoardReplyServiceImpl implements StudyGroupBoardReplyService {

    private final StudyGroupBoardReplyMapper studyGroupBoardReplyMapper;

    @Autowired
    public StudyGroupBoardReplyServiceImpl(StudyGroupBoardReplyMapper studyGroupBoardReplyMapper) {
        this.studyGroupBoardReplyMapper = studyGroupBoardReplyMapper;
    }

    // 댓글 아이디로 대댓글 조회
    @Override
    public List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByCommentId(Long commentId) {
        List<StudyGroupBoardReplyDTO> replies =
                studyGroupBoardReplyMapper.findStudyGroupBoardRepliesByCommentId(commentId);
        if(replies == null || replies.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_REPLY);
        }
        return replies;
    }

    // 그룹원 아이디로 대댓글 조회
    @Override
    public List<StudyGroupBoardReplyDTO> findStudyGroupBoardRepliesByMemberId(Long memberId) {
        List<StudyGroupBoardReplyDTO> replies =
                studyGroupBoardReplyMapper.findStudyGroupBoardRepliesByMemberId(memberId);
        if(replies == null || replies.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_BOARD_REPLY);
        }
        return replies;
    }

}
