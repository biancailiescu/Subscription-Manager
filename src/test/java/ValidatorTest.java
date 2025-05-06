/** Clasa pentru testarea validarilor
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
import com.example.subscription_manager.controller.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testValidareParolaValid() {
        assertTrue(Validator.ValidareParola("Test123456!"));
        assertTrue(Validator.ValidareParola("Parola123456#"));
        assertTrue(Validator.ValidareParola("AbcDef123456@"));
    }

    @Test
    public void testValidareParolaInvalid() {
        assertFalse(Validator.ValidareParola("test123!")); // Fără literă mare
        assertFalse(Validator.ValidareParola("Test12!")); // Mai puțin de trei cifre
        assertFalse(Validator.ValidareParola("TestABCD123")); // Fără caracter special
        assertFalse(Validator.ValidareParola("Te1!")); // Prea scurt
        assertFalse(Validator.ValidareParola(null)); // Null
    }

    @Test
    public void testValidareEmailValid() {
        assertTrue(Validator.ValidareEmail("test@example.com"));
        assertTrue(Validator.ValidareEmail("user.name@domain.com"));
        assertTrue(Validator.ValidareEmail("test123@example.com"));
    }

    @Test
    public void testValidareEmailInvalid() {
        assertFalse(Validator.ValidareEmail("test@example")); // Fără .com
        assertFalse(Validator.ValidareEmail("test@example.org")); // Nu se termină cu .com
        assertFalse(Validator.ValidareEmail("test.example.com")); // Fără @
        assertFalse(Validator.ValidareEmail("")); // String gol
        assertFalse(Validator.ValidareEmail(null)); // Null
    }
}
