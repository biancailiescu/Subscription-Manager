/** Clasa pentru Administrarea Utilizatorilor
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */

package com.example.subscription_manager.controller;

import com.example.subscription_manager.model.User;
import com.example.subscription_manager.service.UserService;
import com.example.subscription_manager.service.ServiceStatisticiSubscriptii;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/web/profile")
public class ControllerUser {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceStatisticiSubscriptii statisticsService;

    @GetMapping
    public String getPaginaProfil(@SessionAttribute(value = "loggedInUser", required = false) User loggedInUser, Model model) {
        if (loggedInUser == null) {

            model.addAttribute("loggedIn", false);
        } else {

            model.addAttribute("loggedIn", true);
            model.addAttribute("username", loggedInUser.getNume());
            model.addAttribute("userID", loggedInUser.getUserID());
            model.addAttribute("email", loggedInUser.getEmail());

            model.addAttribute("totalMonthlyCost", statisticsService.calculeazaPretulLunar(loggedInUser.getUserID()));
            model.addAttribute("categoryPercentages", statisticsService.calculeazaProcentajulPeCategorii(loggedInUser.getUserID()));
        }
        return "profile";
    }
}
