/** Clasa pentru Autentificare
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */

package com.example.subscription_manager.controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.subscription_manager.model.User;
import com.example.subscription_manager.service.UserService;

@Controller
@RequestMapping("/web/auth")
public class ControllerAutentificare {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/autentificare")
    public String autentificare(@RequestParam String email,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        try {
            User user = userService.autentificare(email, password);
            if (user != null) {
                session.setAttribute("loggedInUser", user);
                return "redirect:/web/profile";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials");
        }
        return "redirect:/web/profile";
    }

    @PostMapping("/inregistrare")
    public String inregistrare(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        try {
            if (!Validator.ValidareEmail(email)) {
                redirectAttributes.addFlashAttribute("error", "Invalid email format. Please use example@domain.com");
                return "redirect:/web/profile";
            }

            if (!Validator.ValidareParola(password)) {
                redirectAttributes.addFlashAttribute("error", "Password must contain at least one uppercase letter, three digits, and one special character.");
                return "redirect:/web/profile";
            }

            User newUser = new User();
            newUser.setNume(username);
            newUser.setEmail(email);
            newUser.setPassword(password);

            userService.inregistrareUser(newUser);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please autentificate.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
        }
        return "redirect:/web/profile";
    }

    @PostMapping("/deconectare")
    public String deconectare(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been successfully logged out.");
        return "redirect:/web/profile";
    }
}
