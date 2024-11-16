package com.springcooler.sgma.studygroupcategory.query.repository;

import com.springcooler.sgma.studygroupcategory.query.dto.StudyGroupCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyGroupCategoryMapper {

    // 스터디그룹 카테고리 전체 조회
    List<StudyGroupCategoryDTO> findAllStudyGroupCategories();

    // 스터디그룹 카테고리 단건 조회(카테고리 아이디)
    StudyGroupCategoryDTO findStudyGroupCategoryByCategoryId(Integer categoryId);

}
