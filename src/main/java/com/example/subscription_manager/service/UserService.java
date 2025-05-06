/** Clasa pentru definirea metodelor pentru user (register, login, delete, get)
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.service;

import com.example.subscription_manager.model.User;
import java.util.List;
public interface UserService {

    User inregistrareUser(User user);
    User autentificare(String email, String password);
    void stergeCont(Integer userID);
    User getUserDupaID(Integer id);
    List<User> getTotiUserii();
}