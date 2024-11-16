package com.springcooler.sgma.studygroupcategory.query.controller;

import com.springcooler.sgma.studygroupcategory.common.ResponseDTO;
import com.springcooler.sgma.studygroupcategory.query.dto.StudyGroupCategoryDTO;
import com.springcooler.sgma.studygroupcategory.query.service.StudyGroupCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("queryStudyGroupCategoryController")
@RequestMapping("/api/study-group/categories")
public class StudyGroupCategoryController {

    private final StudyGroupCategoryService studyGroupCategoryService;

    @Autowired
    public StudyGroupCategoryController(StudyGroupCategoryService studyGroupCategoryService) {
        this.studyGroupCategoryService = studyGroupCategoryService;
    }

    // 스터디그룹 카테고리 전체 조회
    @GetMapping
    public ResponseDTO<?> findAllStudyGroupCategories() {
        List<StudyGroupCategoryDTO> categories = studyGroupCategoryService.findAllStudyGroupCategories();
        return ResponseDTO.ok(categories);
    }

    // 스터디그룹 카테고리 단건 조회(카테고리 아이디)
    @GetMapping("/{categoryId}")
    public ResponseDTO<?> findStudyGroupCategoryByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        StudyGroupCategoryDTO category = studyGroupCategoryService.findStudyGroupCategoryByCategoryId(categoryId);
        return ResponseDTO.ok(category);
    }

}
