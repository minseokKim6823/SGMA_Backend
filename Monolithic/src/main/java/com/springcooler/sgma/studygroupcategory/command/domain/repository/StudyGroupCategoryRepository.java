package com.springcooler.sgma.studygroupcategory.command.domain.repository;

import com.springcooler.sgma.studygroupcategory.command.domain.aggregate.StudyGroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyGroupCategoryRepository extends JpaRepository<StudyGroupCategory, Integer> {
}
