package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Account;
import com.myBudget.myBudget.repositories.AccountRepository;
import com.myBudget.myBudget.services.AccountValidationService;
import com.myBudget.myBudget.services.RegisterValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import com.myBudget.myBudget.security.JwtUtil;

@RestController
public class AccountController {

    // AccountRepository => accéder aux methods CRUD 
    @Autowired
    private AccountRepository accountRepository;

    // J'injecte mon fichier pour valider les données du new account
    @Autowired
    private AccountValidationService accountValidationService;

    /*-----------Afficher les comptes d'un client spécifique----------*/
    @GetMapping("/{clientId}/accounts")
    public ResponseEntity<Iterable<Account>> getClientAccounts(@PathVariable Integer clientId, HttpServletRequest request) {
        // j'appelle ma méthode pour extraire le JWT de la requête front-end
        String jwtToken = JwtUtil.extractJwtFromRequest(request);

        if (jwtToken != null && JwtUtil.validateToken(jwtToken)) {
            // le token existe et il correspond au user connecté
            Iterable<Account> accounts = accountRepository.findByClientId(clientId);
            
            return ResponseEntity.ok(accounts);
        } else {
            // le token n'existe pas ou il ne correspond pas au user connecté
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /*-----------------Afficher tous les comptes-------------------*/
    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts() {
        Iterable<Account> accounts = accountRepository.findAll();

        // ResponseEntity est géré automatiquement avec "findAll()"
        return accounts;
    }

    /*----------------Afficher le compte par son Id-----------------*/
    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        // ResponseEntity est géré automatiquement avec "findBy()"
        return optionalAccount.orElse(null);
    }    

    /*-----------------Créer un nouveau compte-------------------*/
    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody Account newAccount) {

        // Je check que les données reçues sont au format correct
        String validationError = accountValidationService.accountCreationValidation(
            newAccount.getName(),
            newAccount.getBank()
        );

        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        // J'enregistre le nouveau compte en BDD
        Account savedAccount = accountRepository.save(newAccount);

        // ResponseEntity => envoi statut 201 + infos du nouveau compte
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    //************************ A TRAVAILLER **************************/
    /*------------Modifier les infos d'un compte par son Id-----------*/
    @PatchMapping("/account/{id}")
    public Account updateAccount(@PathVariable Integer id, Account updatedAccount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        return optionalAccount.map(account -> {
            // Je remplace les infos du compte existant par les infos du nouveau
            account.setName(updatedAccount.getName());
            account.setBank(updatedAccount.getBank());
            account.setClientId(updatedAccount.getClientId());
    
            // J'enregistre les infos en BDD
            accountRepository.save(account);
    
            return account;
        }).orElse(null);
    }
    //******************************************************************/

    /*----------------Supprimer un compte par son Id-----------------*/
    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent()) {
            // Compte non trouvé => j'envoi un statut 404 + message d'erreur
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account not found with id: " + id);
            
        } else {
            /*Account account = optionalAccount.get();*/
            accountRepository.delete(optionalAccount.get());

            // Suppression ok => j'envoi un statut 204
            return ResponseEntity.noContent().build();
        }
    }
}