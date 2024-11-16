package com.springcooler.sgma.recruitmentboardcomment.query.service;

import com.springcooler.sgma.recruitmentboardcomment.query.dto.RecruitmentBoardCommentDTO;
import com.springcooler.sgma.recruitmentboardcomment.query.repository.RecruitmentBoardCommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.springcooler.sgma.recruitmentboardcomment.common.Template.getSqlSession;


@Service
@Slf4j
public class RecruitmentBoardCommentService {
//    private RecruitmentBoardCommentMapper recruitmentBoardCommentMapper;

    public List<RecruitmentBoardCommentDTO> studyGroupRecruitmentComment(Long recruitmentBoardId) {
        SqlSession sqlSession = getSqlSession();
        RecruitmentBoardCommentMapper mapper = sqlSession.getMapper(RecruitmentBoardCommentMapper.class);
        List<RecruitmentBoardCommentDTO> studyGroupRecruitment = mapper.studyGroupRecruitmentCommentDTO1(recruitmentBoardId);
        return studyGroupRecruitment;
    }

}
