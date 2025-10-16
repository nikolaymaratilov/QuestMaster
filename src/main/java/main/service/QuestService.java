package main.service;

import lombok.extern.slf4j.Slf4j;
import main.model.Quest;
import main.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuestService {

    private final QuestRepository questRepository;

    @Autowired
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public List<Quest> getAllQuests() {

        return questRepository.findAll();
    }
}
