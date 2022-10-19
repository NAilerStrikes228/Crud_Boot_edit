package com.springbootkata.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springbootkata.springboot.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userService.findUserByUsername(principal.getName()));
        return "user";
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}