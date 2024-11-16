package com.springcooler.sgma.choice.query.repository;

import com.springcooler.sgma.choice.query.dto.ChoiceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChoiceMapper {
    List<ChoiceDTO> findAllChoices();

    List<ChoiceDTO> findChoicesByProblemId(Long problemId);
}
