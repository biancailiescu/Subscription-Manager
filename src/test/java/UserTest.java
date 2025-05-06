/** Clasa pentru testarea metodelor pentru user
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.subscription_manager.model.User;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testSetAndGetID() {
        user.setID(1);
        assertEquals(1, user.getID());
    }

    @Test
    void testSetAndGetNume() {
        String nume = "Test User";
        user.setNume(nume);
        assertEquals(nume, user.getNume());
    }

    @Test
    void testSetAndGetEmail() {
        String email = "test@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        String password = "TestPassword123!";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
}
