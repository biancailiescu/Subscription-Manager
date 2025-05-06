/** Clasa pentru Redirectionarea spre pagina Home
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */

package com.example.subscription_manager.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}