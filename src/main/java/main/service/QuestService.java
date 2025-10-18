package main.service;

import lombok.extern.slf4j.Slf4j;
import main.model.Player;
import main.model.PlayerClass;
import main.model.Quest;
import main.repository.QuestRepository;
import main.web.dto.CreateQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class QuestService {

    private final QuestRepository questRepository;
    private final ItemService itemService;

    @Autowired
    public QuestService(QuestRepository questRepository, ItemService itemService) {
        this.questRepository = questRepository;
        this.itemService = itemService;
    }

    public List<Quest> getAllQuests() {

        return questRepository.findAllByOrderByCreatedOnDescXpDesc();
    }

    public void createNewQuest(CreateQuest createQuest, Player player) {

        Quest quest = Quest.builder()
                .title(createQuest.getTitle())
                .description(createQuest.getDescription())
                .xp(createQuest.getXp())
                .bannerUrl(createQuest.getBannerUrl())
                .eligibleClass(createQuest.getEligibleClass())
                .rewardItem(itemService.getById(createQuest.getRewardItemId()))
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .createdBy(player.getNickname())
                .updatedBy(player.getNickname())
                .build();

        questRepository.save(quest);
    }

    public List<Quest> getAllByClass(PlayerClass playerClass) {

        return questRepository.findAllByEligibleClassOrderByCreatedOnDesc(playerClass);
    }
}
