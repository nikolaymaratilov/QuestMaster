package main.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import main.model.Player;
import main.service.ItemService;
import main.service.PlayerService;
import main.service.QuestService;
import main.web.dto.CreateQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;
    private final ItemService itemService;
    private final PlayerService playerService;

    @Autowired
    public QuestController(QuestService questService, ItemService itemService, PlayerService playerService) {
        this.questService = questService;
        this.itemService = itemService;
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView showQuestPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("quests");
        modelAndView.addObject("allQuests",questService.getAllQuests());
        modelAndView.addObject("allItems",itemService.getAllItems());
        modelAndView.addObject("createQuest",new CreateQuest());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView createQuest(@Valid CreateQuest createQuest, BindingResult bindingResult, HttpSession session){

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("quests");
            modelAndView.addObject("allQuests",questService.getAllQuests());
            modelAndView.addObject("allItems",itemService.getAllItems());

            return modelAndView;
        }

        UUID playerId = (UUID) session.getAttribute("user_id");
        Player player = playerService.getById(playerId);
        questService.createNewQuest(createQuest,player);

        return new ModelAndView("redirect:/quests");
    }
}
