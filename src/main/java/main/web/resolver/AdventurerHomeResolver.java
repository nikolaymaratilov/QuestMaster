package main.web.resolver;

import main.model.Player;
import main.model.PlayerRole;
import main.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AdventurerHomeResolver implements HomeResolver{
    private final QuestService questService;

    @Autowired
    public AdventurerHomeResolver(QuestService questService) {
        this.questService = questService;
    }

    @Override
    public boolean supports(PlayerRole playerRole) {
        return playerRole == PlayerRole.ADVENTURER;

    }

    @Override
    public String getViewName() {

        return "adventurer-home";
    }

    @Override
    public Map<String, Object> getModelData(Player player) {

        return Map.of(
                "adventurer",player,
                "quests",questService.getAllByClass(player.getPlayerClass())
        );
    }
}
