package de.dhbw.ase.todoapp.plugins.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.application.UserService;
import de.dhbw.ase.todoapp.domain.exceptions.UserAlreadyRegisteredException;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController
{
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String renderLoginPage(HttpSession session, Model model)
    {
        return "login";
    }


    @PostMapping("/login")
    public String authenticateUser(@RequestParam("mailAdress") String mailAdress,
                                   @RequestParam("password") String password,
                                   HttpSession session, Model model)
    {
        try
        {
            UUID userId = userService.authenticateUser(mailAdress, password);
            if (userId != null)
            {
                session.setAttribute("userId", userId);
                return "redirect:/todo";
            }
            else
            {
                model.addAttribute("error", "Ungültige E-Mail-Adresse oder Passwort");
                return "login";
            }
        }
        catch (RuntimeException e)
        {
            model.addAttribute("error", "Login fehlgeschlagen");
            return "login";
        }
    }


    @GetMapping("/register")
    public String renderRegisterPage(HttpSession session, Model model)
    {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam("mailAdress") String mailAdress,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               HttpSession session, Model model)
    {
        if (password.matches(confirmPassword))
        {
            try
            {
                if (userService.registerUser(mailAdress, password) != null)
                {
                    return "redirect:/login";
                }
                else
                {
                    model.addAttribute("error", "Registrierung fehlgeschlagen");
                    return "register";
                }
            }
            catch (UserAlreadyRegisteredException e)
            {
                model.addAttribute("error", "Es existiert bereits ein Nutzer mit dieser E-Mail-Adresse");
                return "register";
            }
        }
        model.addAttribute("error", "Die Passwörter stimmen nicht überein");
        return "register";
    }
}
