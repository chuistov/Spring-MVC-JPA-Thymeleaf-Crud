package com.chuistov.mvc.controllers;

import com.chuistov.mvc.entities.User;
import com.chuistov.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User("name", "last name", 0));
        return "user/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        System.out.println("got user from form");
        userService.save(user);
        return "redirect:/user";
    }

}
