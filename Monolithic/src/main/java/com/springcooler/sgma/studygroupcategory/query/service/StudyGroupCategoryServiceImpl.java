package com.springcooler.sgma.studygroupcategory.query.service;

import com.springcooler.sgma.studygroupcategory.common.exception.CommonException;
import com.springcooler.sgma.studygroupcategory.common.exception.ErrorCode;
import com.springcooler.sgma.studygroupcategory.query.dto.StudyGroupCategoryDTO;
import com.springcooler.sgma.studygroupcategory.query.repository.StudyGroupCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupCategoryServiceImpl implements StudyGroupCategoryService {

    private final StudyGroupCategoryMapper studyGroupCategoryMapper;

    @Autowired
    public StudyGroupCategoryServiceImpl(StudyGroupCategoryMapper studyGroupCategoryMapper) {
        this.studyGroupCategoryMapper = studyGroupCategoryMapper;
    }

    // 스터디그룹 카테고리 전체 조회
    @Override
    public List<StudyGroupCategoryDTO> findAllStudyGroupCategories() {
        List<StudyGroupCategoryDTO> categories = studyGroupCategoryMapper.findAllStudyGroupCategories();
        if (categories == null || categories.isEmpty()) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_CATEGORY);
        }
        return categories;
    }

    // 스터디그룹 카테고리 단건 조회(카테고리 아이디)
    @Override
    public StudyGroupCategoryDTO findStudyGroupCategoryByCategoryId(Integer categoryId) {
        StudyGroupCategoryDTO category = studyGroupCategoryMapper.findStudyGroupCategoryByCategoryId(categoryId);
        if (category == null) {
            throw new CommonException(ErrorCode.NOT_FOUND_STUDY_GROUP_CATEGORY);
        }
        return category;
    }

}
