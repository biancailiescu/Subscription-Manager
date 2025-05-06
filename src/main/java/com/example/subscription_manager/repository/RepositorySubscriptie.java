/** Interfata pentru gasirea subscriptiilor in functie de ID-ul utilizatorului sau categoria subscriptiilor
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.repository;

import com.example.subscription_manager.model.Subscriptie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RepositorySubscriptie extends JpaRepository<Subscriptie, Integer> {
    List<Subscriptie> findByUser_UserID(Integer userID);
    List<Subscriptie> findByUser_UserID(Integer userID, Sort sort);
    Subscriptie findBySubscriptionIDAndUserUserID(Integer subscriptionID, Integer userID);
    List<Subscriptie> findByUser_UserIDAndCategory(Integer userID, String category);

}
