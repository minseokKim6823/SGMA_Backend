package com.springcooler.sgma.studygroupcategory.query.service;

import com.springcooler.sgma.studygroupcategory.query.dto.StudyGroupCategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class StudyGroupCategoryTests {

    @Autowired
    private StudyGroupCategoryService studyGroupCategoryService;

    @DisplayName("스터디그룹 카테고리 전체 조회 테스트")
    @Test
    void testFindAllCategories() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<StudyGroupCategoryDTO> categories = studyGroupCategoryService.findAllStudyGroupCategories();
                    categories.forEach(x -> log.info(x.toString()));
                }
        );
    }

    @DisplayName("스터디그룹 카테고리 단건 조회(카테고리 아이디) 테스트")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void testFindStudyGroupCategoryByCategoryId(Integer categoryId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    StudyGroupCategoryDTO category =
                            studyGroupCategoryService.findStudyGroupCategoryByCategoryId(categoryId);
                    log.info(category.toString());
                }
        );
    }
}
