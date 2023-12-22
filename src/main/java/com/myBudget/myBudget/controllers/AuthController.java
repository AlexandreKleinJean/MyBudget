package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.User;
import com.myBudget.myBudget.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

@RestController
public class AuthController {

    // J'implémente le UserRepository pour accéder aux methods CRUD 
    @Autowired
    private UserRepository userRepository;

    // J'implémente le PasswordEncord pour accéder à la method de hachage
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*-----------------S'inscrire (Signin)-----------------*/
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User newUser) {
        
        // Je remplace le password brut du user par son password hachée
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        
        // J'enregistre le nouveau user en BDD
        User savedUser = userRepository.save(newUser);

        // J'envoi un code 201 + les infos du nouveau user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /*------------------Se connecter (Login)-------------------*/
    @PostMapping("/login")
    public ResponseEntity<User> login(User loggedUser) {

        // Je cherche un user qui a l'email correspondant
        User user = userRepository.findByEmail(loggedUser.getEmail());

        // Le user existe
        if (user != null) {

            // Le password correspond
            if (BCrypt.checkpw(loggedUser.getPassword(), user.getPassword())) {
                return ResponseEntity.ok(user);
            
            // Le password ne correspond pas
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        // Le user n'existe pas
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

