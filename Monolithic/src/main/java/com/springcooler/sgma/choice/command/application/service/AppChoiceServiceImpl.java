package com.springcooler.sgma.choice.command.application.service;

import com.springcooler.sgma.choice.command.application.dto.ChoiceDTO;
import com.springcooler.sgma.choice.command.domain.aggregate.entity.Choice;
import com.springcooler.sgma.choice.command.domain.aggregate.entity.ChoicePK;
import com.springcooler.sgma.choice.command.domain.aggregate.vo.ProblemVO;
import com.springcooler.sgma.choice.command.domain.repository.ChoiceRepository;
import com.springcooler.sgma.choice.common.exception.CommonException;
import com.springcooler.sgma.choice.common.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppChoiceServiceImpl implements AppChoiceService {

    private final ChoiceRepository choiceRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public AppChoiceServiceImpl(ChoiceRepository choiceRepository , ModelMapper modelMapper) {
        this.choiceRepository = choiceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Choice registChoice(ChoiceDTO newChoiceDTO) {

        ChoicePK choicePK = new ChoicePK(newChoiceDTO.getProblemId(), newChoiceDTO.getChoiceNum());
        Choice choice = new Choice(choicePK, newChoiceDTO.getContent());
        return choiceRepository.save(choice);

    }


    @Transactional
    @Override
    public ChoiceDTO modifyChoice(ChoiceDTO modifyChoiceDTO) {
        ChoicePK choicePK = new ChoicePK(modifyChoiceDTO.getProblemId(), modifyChoiceDTO.getChoiceNum());
        Choice existingChoice = choiceRepository.findById(choicePK).orElseThrow(EntityNotFoundException::new);
        existingChoice.setContent(modifyChoiceDTO.getContent());
        Choice modifiedChoice = choiceRepository.save(existingChoice);
        return new ChoiceDTO(modifiedChoice.getChoicePK().getProblemId(), modifiedChoice.getChoicePK().getChoiceNum(), modifiedChoice.getContent());
    }


    @Override
    public ProblemVO registChoices(ProblemVO problemVO) {
            long problemID = problemVO.getProblemId();
            List<String> choices = problemVO.getChoices();
            List<Choice> choicesToInsert = new ArrayList<>();
            for (int i = 0; i < choices.size(); i++) {
                ChoicePK choicePK = new ChoicePK(problemID, i + 1);
                log.info("i: {}", i);
                Choice newChoice = new Choice(choicePK, choices.get(i));
                choicesToInsert.add(newChoice);
            }
            choicesToInsert = choiceRepository.saveAll(choicesToInsert);
            ProblemVO insertedInfo = new ProblemVO(problemVO.getProblemId(), choicesToInsert.stream().map(x -> x.getContent()).collect(Collectors.toList()));
            return insertedInfo;
    }
}

