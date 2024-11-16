package com.springcooler.sgma.choice.command.application.service;

import com.springcooler.sgma.choice.command.domain.aggregate.entity.Choice;
import com.springcooler.sgma.choice.command.domain.aggregate.entity.ChoicePK;
import com.springcooler.sgma.choice.command.domain.repository.ChoiceRepository;

import com.springcooler.sgma.problem.command.domain.aggregate.entity.ProblemVO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppChoiceServiceImpl implements AppChoiceService {

    private final ChoiceRepository choiceRepository;
    @Autowired
    public AppChoiceServiceImpl(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;

    }


    @Transactional
    @Override
    public ProblemVO registChoices(ProblemVO problemVO) {
            Long problemID = problemVO.getProblemId();
            List<String> choices = problemVO.getChoices();
            List<Choice> choicesToInsert = new ArrayList<>();
            for (int i = 0; i < choices.size(); i++) {
                ChoicePK choicePK = new ChoicePK(problemID, i + 1);
                log.info("i: {}", i);
                Choice newChoice = new Choice(choicePK, choices.get(i));
                choicesToInsert.add(newChoice);
            }
            choicesToInsert = choiceRepository.saveAll(choicesToInsert);
            ProblemVO insertedInfo = new ProblemVO(problemID, choicesToInsert.stream().map(x -> x.getContent()).collect(Collectors.toList()));
            return insertedInfo;
    }
}

