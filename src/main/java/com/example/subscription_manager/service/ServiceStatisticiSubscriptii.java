/** Clasa pentru statisticile privind subscriptiile
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.service;

import com.example.subscription_manager.model.Subscriptie;
import com.example.subscription_manager.repository.RepositorySubscriptie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceStatisticiSubscriptii {
    @Autowired
    private RepositorySubscriptie repositorySubscriptie;

    public double calculeazaPretulLunar(Integer userId) {
        List<Subscriptie> subscriptii = repositorySubscriptie.findByUser_UserID(userId);
        return subscriptii.stream().mapToDouble(Subscriptie::getPrice).sum();
    }

    public Map<String, Double> calculeazaProcentajulPeCategorii(Integer userId) {
        List<Subscriptie> subscriptii = repositorySubscriptie.findByUser_UserID(userId);
        double totalCost = calculeazaPretulLunar(userId);

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Subscriptie subscriptie : subscriptii) {
            String category = subscriptie.getCategory();
            double price = subscriptie.getPrice();
            categoryTotals.merge(category, price, Double::sum);
        }

        return categoryTotals.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (e.getValue() / totalCost) * 100
                ));
    }
}
