/** Clasa pentru Extragerea datelor din tabela User a bazei de date
 * @author Iliescu Bianca-Ana-Maria
 * @version 03 Ianuarie 2025
 */
package com.example.subscription_manager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Subscriptie> subscriptii;

    public int getID(){
        return userID;
    }

    public String getEmail(){
        return email;
    }
   public String getNume(){
        return username;
    }

    public void setID(int id){
        userID = id;
    }
    public void setNume(String name){
       username = name;
    }
}
