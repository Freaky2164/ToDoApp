package de.dhbw.ase.todoapp.plugins.controller;


import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.dhbw.ase.todoapp.domain.entities.notification.Notification;
import de.dhbw.ase.todoapp.domain.vo.Name;
import de.dhbw.ase.todoapp.domain.vo.WebHook;
import jakarta.servlet.http.HttpSession;


@Controller
public class NotificationController extends ViewController
{
    @PostMapping("/createNotification")
    public String createNotification(@RequestParam("name") String name, @RequestParam("webHookUrl") String webHookUrl,
                                     HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Notification notification = new Notification(userId, new Name(name), new WebHook(webHookUrl));
        notificationService.createNotification(notification);
        registerObserver(notification);
        return "redirect:/todo";
    }


    @PostMapping("/deleteNotification")
    public String deleteNotification(@RequestParam("notificationId") UUID notificationId, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Notification> notificationOptional = notificationService.findById(notificationId);
        notificationOptional.ifPresent(notification ->
        {
            notificationService.deleteNotification(notification);
            unregisterObserver(notification);
        });
        return "redirect:/todo";
    }


    @PostMapping("/editNotification")
    public String editNotification(@RequestParam("notificationId") UUID notificationId, @RequestParam("name") String name,
                                   @RequestParam("webHookUrl") String webHookUrl, HttpSession session, Model model)
    {
        UUID userId = (UUID)session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/login";
        }

        Optional<Notification> notificationOptional = notificationService.findById(notificationId);
        notificationOptional.ifPresent(notification ->
        {
            notification.changeName(new Name(name));
            notification.setWebHook(new WebHook(webHookUrl));
            notificationService.createNotification(notification);
        });
        return "redirect:/todo";
    }
}