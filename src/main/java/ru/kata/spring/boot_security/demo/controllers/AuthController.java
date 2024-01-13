package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AuthController {
    private final UserService service;
    private final RoleService roleService;

    @Autowired
    public AuthController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping
    public String printAll(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allUsers", service.getAll());
        model.addAttribute("allRoles", roleService.getAll());
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "admin/index";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String remove(@RequestParam("id") Long id) {
        service.removeById(id);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        service.update(user, id);
        return "redirect:/admin";
    }
}
