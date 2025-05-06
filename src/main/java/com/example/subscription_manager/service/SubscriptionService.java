/** Clasa pentru definirea metodelor pentru subscriptii (add, delete, modify, get)
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.service;

import com.example.subscription_manager.model.Subscriptie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

public interface SubscriptionService {
    Subscriptie adaugaSubscriptie(Subscriptie subscriptie);
    void stergeSubscriptie(Integer subscriptionID, Integer userID);
    Subscriptie modificaSubscriptie(Integer subscriptionID, Integer userID, float price,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate);
    Subscriptie getSubscriptie(Integer subscriptionID, Integer userID);
    List<Subscriptie> getSubscriptiiUser(Integer userID);
    List<Subscriptie> getSubscriptiiUserDupaCategorie(Integer userID, String category);

    List<Subscriptie> getSubscriptiiUserSortate(Integer userID, Sort sort);
}
