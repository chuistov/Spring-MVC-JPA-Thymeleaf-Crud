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
    public String startCreateUser(Model model) {
        model.addAttribute("user", new User("name", "last name", 0));
        return "user/new";
    }

    @PostMapping()
    public String finishCreateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String startUpdateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String finishUpdateUser(@ModelAttribute("user") User user,
                                   @PathVariable("id") long id) {
        userService.updateUser(user, id);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

}
