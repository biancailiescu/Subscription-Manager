/** Clasa pentru Administrarea Subscriptiilor
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.controller;

import com.example.subscription_manager.model.Subscriptie;
import com.example.subscription_manager.model.User;
import com.example.subscription_manager.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/web/subscriptions")
public class ControllerSubscriptii {

    @Autowired
    private SubscriptionService subscriptionService;


    @PostMapping("/adaugareSubscriptie/{userId}")
    public ResponseEntity<?> adaugareSubscriptie(@SessionAttribute User loggedInUser,
                                                 @RequestParam String name,
                                                 @RequestParam String category,
                                                 @RequestParam float price,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 RedirectAttributes redirectAttributes) {
        try {
            // Validări
            if (name == null || name.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Subscription name cannot be empty.");
                return ResponseEntity.badRequest().body("Subscription name cannot be empty.");
            }

            if (category == null || category.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Category cannot be empty.");
                return ResponseEntity.badRequest().body("Category cannot be empty.");
            }

            if (price <= 0) {
                redirectAttributes.addFlashAttribute("error", "Price must be greater than zero.");
                return ResponseEntity.badRequest().body("Price must be greater than zero.");
            }

            if (startDate.isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("error", "Start date cannot be in the past.");
                return ResponseEntity.badRequest().body("Start date cannot be in the past.");
            }

            // Crearea abonamentului
            Subscriptie newSubscriptie = new Subscriptie();
            newSubscriptie.setName(name);
            newSubscriptie.setCategory(category);
            newSubscriptie.setPrice(price);
            newSubscriptie.setStartDate(startDate);
            newSubscriptie.setUser(loggedInUser);

            subscriptionService.adaugaSubscriptie(newSubscriptie);

            redirectAttributes.addFlashAttribute("success", "Subscriptie added successfully.");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/stergereSubscriptie/{subscriptionID}/user/{userID}")
    public String stergereSubscriptie(@PathVariable Integer subscriptionID, @PathVariable Integer userID) {
        subscriptionService.stergeSubscriptie(subscriptionID, userID);
        return "redirect:/web/subscriptions/user/" + userID;
    }

    @GetMapping("/user/{userID}")
    public String getSubscriptii(@PathVariable Integer userID,
                                 @RequestParam(defaultValue = "subscriptionID") String sortBy,
                                 @RequestParam(defaultValue = "asc") String sortDir,
                                 Model model) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        List<Subscriptie> subscriptii = subscriptionService.getSubscriptiiUserSortate(userID, sort);
        model.addAttribute("subscriptii", subscriptii);
        model.addAttribute("userID", userID);
        return "user-subscriptii";
    }


    @GetMapping("/user/{userID}/category/{category}")
    public String getSubscriptiiDupaCategorie(@PathVariable Integer userID, @PathVariable String category, Model model) {
        List<Subscriptie> subscriptii;
        if ("all".equals(category)) {
            subscriptii = subscriptionService.getSubscriptiiUser(userID);
        } else {
            subscriptii = subscriptionService.getSubscriptiiUserDupaCategorie(userID, category);
        }
        model.addAttribute("subscriptii", subscriptii);
        model.addAttribute("userID", userID);
        model.addAttribute("category", category);
        return "user-subscriptii";
    }

    @PostMapping("/update/user/{userID}")
    public ResponseEntity<?> modificaSubscriptie(@PathVariable Integer userID,
                                                 @RequestParam Integer subscriptionID,
                                                 @RequestParam float price,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate) {
        try {
            // Validări
            if (price <= 0) {
                return ResponseEntity.badRequest().body("Price must be greater than zero.");
            }

            if (startDate.isBefore(LocalDate.now())) {
                return ResponseEntity.badRequest().body("Start date cannot be in the past.");
            }

            // Actualizează abonamentul
            Subscriptie updatedSubscriptie = subscriptionService.modificaSubscriptie(subscriptionID, userID, price, startDate);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
