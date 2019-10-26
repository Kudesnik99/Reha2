package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.User;
import ru.tsystems.reha.service.ServiceException;
import ru.tsystems.reha.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(TreatmentController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model theModel, Authentication authentication) {
        try {
            List<User> theUsers = userService.getUsers();
            theModel.addAttribute("users", theUsers);
            UserDto userDto = (UserDto) authentication.getPrincipal();
            theModel.addAttribute("userDto", userDto);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            theModel.addAttribute("exception", e.getError().getMessage());
        }
        return "list-users";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "user-form";
    }

//    @PostMapping("/saveUser")
//    public String saveUser(@ModelAttribute("user") User theUser) {
//        userService.saveUser(theUser);
//        return "redirect:/user/list";
//    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        try {
            //User theUser = new User();
            userService.saveUser(user);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            //theModel.addAttribute("exception", e.getError().getMessage());
        }
        return "redirect:/user/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {
        try {
            User theUser = userService.getUser(theId);
            theModel.addAttribute("user", theUser);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            //theModel.addAttribute("exception", e.getError().getMessage());
        }
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        userService.deleteUser(theId);
        return "redirect:/user/list";
    }
}
