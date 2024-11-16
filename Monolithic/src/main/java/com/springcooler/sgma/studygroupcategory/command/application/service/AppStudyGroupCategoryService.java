package com.springcooler.sgma.studygroupcategory.command.application.service;

import com.springcooler.sgma.studygroupcategory.command.application.dto.StudyGroupCategoryDTO;

public interface AppStudyGroupCategoryService {

    // 스터디그룹 카테고리 생성
    StudyGroupCategoryDTO registStudyGroupCategory(StudyGroupCategoryDTO newCategory);

    // 스터디그룹 카테고리 삭제
    void deleteStudyGroupCategory(Integer categoryId);

}
