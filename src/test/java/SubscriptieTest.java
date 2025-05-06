/** Clasa pentru testarea metodelor pentru Subscriptii
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */

import com.example.subscription_manager.model.Subscriptie;
import com.example.subscription_manager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptieTest {

    private Subscriptie subscriptie;
    private User user;

    @BeforeEach
    void setUp() {
        subscriptie = new Subscriptie();
        user = new User();
        user.setID(1);
        user.setNume("Test User");
        user.setEmail("test@example.com");
    }

    @Test
    void testSetAndGetUser() {
        subscriptie.setUser(user);
        assertEquals(1, subscriptie.getUserId());
        assertEquals(user, subscriptie.getUser());
    }

    @Test
    void testSetAndGetStartDate() {
        LocalDate date = LocalDate.now();
        subscriptie.setStartDate(date);
        assertEquals(date, subscriptie.getStartDate());
    }

    @Test
    void testSetAndGetPrice() {
        float price = 9.99f;
        subscriptie.setPret(price);
        assertEquals(price, subscriptie.getPrice());
    }

    @Test
    void testGetZilePanaLaUrmatoareaPlata() {
        LocalDate today = LocalDate.now();
        subscriptie.setStartDate(today.minusDays(15));
        long zile = subscriptie.getZilePanaLaUrmatoareaPlata();
        assertTrue(zile >= 0 && zile <= 31);
    }
}
