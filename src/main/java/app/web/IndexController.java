package app.web;

import app.user.model.Country;
import app.user.model.User;
import app.user.property.UserProperties;
import app.user.service.UserService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private final UserService userService;
    private final UserProperties userProperties;

    public IndexController(UserService userService, UserProperties userProperties) {
        this.userService = userService;
        this.userProperties = userProperties;
    }

    @GetMapping("/")
    public String getIndexPage(){

        return "index";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest",new LoginRequest());

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid LoginRequest loginRequest,BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ModelAndView("login");
        }

        userService.login(loginRequest);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest",new RegisterRequest());
        modelAndView.addObject("countryList", Country.values());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid RegisterRequest registerRequest, BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            return new ModelAndView("register");
        }

        userService.register(registerRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(){

       User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home");
        modelAndView.addObject("user",user);

        return modelAndView;
    }


}
