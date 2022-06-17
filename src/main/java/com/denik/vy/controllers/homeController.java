package com.denik.vy.controllers;

import com.denik.vy.models.User;
import com.denik.vy.services.RoleService;
import com.denik.vy.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class homeController {
    final UserService userService;
    final RoleService roleService;

    public homeController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(ModelMap model){

        model.addAttribute("users", userService.users());
        return "index";
    }
    @GetMapping("/admin/create")
    public String create(ModelMap model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.roles());
        return "create";
    }
    @PostMapping("/admin/create")
    public String create(User user){
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") long userId){
        User user = userService.user(userId);
        user.setPassword(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.roles());
        return "edit";
    }
    @PostMapping("/admin/edit")
    public String edit(User user){
        userService.edit(user);
        return "redirect:/";
    }
    @GetMapping("/admin/delete/{id}")
    public String delete(ModelMap model, @PathVariable("id") long userId){
        User user = userService.user(userId);
        user.setPassword(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.roles());
        return "delete";
    }
    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") long userId){
        userService.delete(userId);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model){
        model.addAttribute("users", userService.users());
        return "profile";
    }
}
