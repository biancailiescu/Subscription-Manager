/** Clasa pentru implementarea metodelor pentru subscriptii (add, delete, modify, get)
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.service;

import com.example.subscription_manager.model.Subscriptie;
import com.example.subscription_manager.repository.RepositorySubscriptie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private RepositorySubscriptie repositorySubscriptie;

    @Override
    public Subscriptie adaugaSubscriptie(Subscriptie subscriptie) {
        return repositorySubscriptie.save(subscriptie);
    }

    @Override
    public void stergeSubscriptie(Integer subscriptionID, Integer userID) {
        Subscriptie subscriptie = repositorySubscriptie.findById(subscriptionID)
                .orElseThrow(() -> new RuntimeException("Subscriptie not found"));
        if (!subscriptie.getUser().getUserID().equals(userID)) {
            throw new RuntimeException("Unauthorized access");
        }
        repositorySubscriptie.deleteById(subscriptionID);
    }
    @Override
    public Subscriptie getSubscriptie(Integer subscriptionID, Integer userID) {
        Subscriptie subscriptie = repositorySubscriptie.findBySubscriptionIDAndUserUserID(subscriptionID, userID);
        if (subscriptie == null) {
            throw new RuntimeException("Subscriptie not found");
        }
        return subscriptie;
    }
    @Override
    public Subscriptie modificaSubscriptie(Integer subscriptionID, Integer userID, float price, LocalDate startDate) {
        Subscriptie subscriptie = getSubscriptie(subscriptionID, userID);
        subscriptie.setPrice(price);
        subscriptie.setStartDate(Date.valueOf(startDate).toLocalDate());
        return repositorySubscriptie.save(subscriptie);
    }

    @Override
    public List<Subscriptie> getSubscriptiiUser(Integer userID) {
        return repositorySubscriptie.findByUser_UserID(userID);
    }
    @Override
    public List<Subscriptie> getSubscriptiiUserDupaCategorie(Integer userID, String category) {
        return repositorySubscriptie.findByUser_UserIDAndCategory(userID, category);
    }
    @Override
    public List<Subscriptie> getSubscriptiiUserSortate(Integer userID, Sort sort) {
        return repositorySubscriptie.findByUser_UserID(userID, sort);
    }
}
