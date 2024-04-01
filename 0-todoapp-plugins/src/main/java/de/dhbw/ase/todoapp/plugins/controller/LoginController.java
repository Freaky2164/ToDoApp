package de.dhbw.ase.todoapp.plugins.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.application.UserService;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController
{
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String renderLoginPage(Model model)
    {
        return "login";
    }


    @PostMapping("/login")
    public String authenticateUser(@RequestParam("mailAdress") String mailAdress,
                                   @RequestParam("password") String password,
                                   HttpSession session)
    {
        UUID userId = userService.authenticateUser(mailAdress, password);
        if (userId != null)
        {
            session.setAttribute("userId", userId);
            return "redirect:/todo";
        }
        else
        {
            return "login";
        }
    }


    @GetMapping("/register")
    public String renderRegisterPage(Model model)
    {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam("mailAdress") String mailAdress, @RequestParam("password") String password)
    {
        if (userService.registerUser(mailAdress, password) != null)
        {
            return "redirect:/login";
        }
        else
        {
            return "register";
        }
    }
}
