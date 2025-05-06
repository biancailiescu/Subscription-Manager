/** Clasa pentru Extragerea datelor din tabela Subscriptions a bazei de date
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "Subscriptions")
@Data
public class Subscriptie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubscriptionID")
    private Integer subscriptionID;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "Price", nullable = false)
    private Float price;

    @Column(name = "Category", nullable = false, length = 50)
    private String category;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    public Integer getUserId() {
        return user != null ? user.getUserID() : null;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setStartDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate){
        this.startDate = startDate;
    }

    public void setPret(float price){
        this.price = price;
    }
    public long getZilePanaLaUrmatoareaPlata() {
        LocalDate today = LocalDate.now();
        LocalDate nextPaymentDate = this.startDate;

        while (nextPaymentDate.isBefore(today) || nextPaymentDate.isEqual(today)) {
            nextPaymentDate = nextPaymentDate.plusMonths(1);
        }

        return today.until(nextPaymentDate).getDays();
    }

}
