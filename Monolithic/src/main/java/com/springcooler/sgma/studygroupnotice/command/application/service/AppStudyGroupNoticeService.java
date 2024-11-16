package com.springcooler.sgma.studygroupnotice.command.application.service;

import com.springcooler.sgma.studygroupnotice.command.application.dto.StudyGroupNoticeDTO;

public interface AppStudyGroupNoticeService {

    StudyGroupNoticeDTO registStudyGroupNotice(StudyGroupNoticeDTO newNotice);

    StudyGroupNoticeDTO modifyStudyGroupNotice(StudyGroupNoticeDTO modifyNotice);

    void deleteStudyGroupNotice(Long noticeId);

}
