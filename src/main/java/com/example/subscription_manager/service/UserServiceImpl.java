/** Clasa pentru implementarea metodelor pentru user (register, login, delete, get)
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.service;

import com.example.subscription_manager.model.User;
import com.example.subscription_manager.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User inregistrareUser(User user) {
        if (repositoryUser.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return repositoryUser.save(user);
    }

    @Override
        public User autentificare(String email, String password) {
            User user = repositoryUser.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }


    @Override
    public void stergeCont(Integer userID) {
        repositoryUser.deleteById(userID);
    }

    @Override
    public User getUserDupaID(Integer id) {
        return repositoryUser.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getTotiUserii() {
        return repositoryUser.findAll();
    }

}
