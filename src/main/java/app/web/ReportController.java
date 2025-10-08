package app.web;

import app.user.model.User;
import app.user.property.UserProperties;
import app.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReportController {

    private final UserService userService;
    private final UserProperties userProperties;

    public ReportController(UserService userService, UserProperties userProperties) {
        this.userService = userService;
        this.userProperties = userProperties;
    }
    @GetMapping("/reports")
    public ModelAndView getReportsPage(){

        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reports");

        modelAndView.addObject("users",users);

        long activeCount = users.stream().filter(User::isActive).count();
        modelAndView.addObject("activeCount",activeCount);

        long adminRole = users.stream().filter(user -> user.getRole().equals("Admin")).count();
        modelAndView.addObject("adminRole",adminRole);

        return modelAndView;
    }
}
