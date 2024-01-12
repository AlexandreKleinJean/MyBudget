package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Transaction;
import com.myBudget.myBudget.repositories.TransactionRepository;
import com.myBudget.myBudget.services.TransactionValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import org.springframework.http.ResponseEntity;



@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    private TransactionValidationService transactionValidationService;

    /*-------------Afficher les transactions par compte-------------*/
    @GetMapping("/{accountId}/transactions")
    // La method retourne => Type Iterable<Transaction>
    public Iterable<Transaction> getAllTransactions(@PathVariable Integer accountId) {
        Iterable<Transaction> transactions;

        // ma logique
        try {
            // je récupère les transactions par accountId
            transactions = transactionRepository.findByAccountId(accountId);

        } catch (Exception e) {
            // ResponseEntity pris en charge par SpringBoot => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transactions by accountId fetch)");
        }

        // je retourne la réponse (try: transactions / catch: "null")
        return transactions;
    }

    /*------------Afficher une transaction par son Id-------------*/
    @GetMapping("/transaction/{id}")
    // La méthode retourne un objet de type Transaction ou une ResponseEntity
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction;
    
        // ma logique
        try {
            // je récupère la transaction (optional renvoie un boolean)
            optionalTransaction = transactionRepository.findById(id);
    
            // si j'obtient true
            if (optionalTransaction.isPresent()) {
                // je stocke la transaction dans ma variable
                Transaction transaction = optionalTransaction.get();
                // ResponseEntity => statut 201 + variable
                return ResponseEntity.ok(transaction);
            } else {
                // ResponseEntity => statut 404 + message d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Transaction with id " + id);
            }
        } catch (Exception e) {
            // EXCEPTION (ResponseEntity pris en charge par SpringBoot) => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction delete)", e);
        }
    }

    /*----------Modifier les infos d'une transaction par son Id---------*/
    @PatchMapping("/transaction/{id}")
    // La method retourne => Type ReponseEntity
    public ResponseEntity<?> updateTransaction(@PathVariable Integer id, Transaction newTransaction) {
        Optional<Transaction> optionalTransaction;
        
        // ma logique
        try{
            // je récupère la transaction (optional renvoie un boolean)
            optionalTransaction = transactionRepository.findById(id);

            if (optionalTransaction.isPresent()) {
                // je récupère la transaction à mettre à jour
                Transaction transactionOnUpdate = optionalTransaction.get();

                // Valider la nouvelle transaction
                /*String validationError = transactionValidationService.validateTransaction(newTransaction);
                
                // Pas de message d'erreur de mon service de validation
                if (validationError == null) {*/
                
                // je remplace ses infos par les infos de la nouvelle
                transactionOnUpdate.setSubject(newTransaction.getSubject());
                transactionOnUpdate.setNote(newTransaction.getNote());
                transactionOnUpdate.setIcon(newTransaction.getIcon());
                transactionOnUpdate.setDate(newTransaction.getDate());
                transactionOnUpdate.setCategory(newTransaction.getCategory());
                transactionOnUpdate.setAmount(newTransaction.getAmount());
                transactionOnUpdate.setAccountId(newTransaction.getAccountId());
        
                // j'enregistre les infos en BDD + stockage dans variable
                Transaction updatedTransaction = transactionRepository.save(transactionOnUpdate);
    
                // ResponseEntity => envoi d'un statut 201 + variable
                return ResponseEntity.ok(updatedTransaction);

                // Message d'erreur de mon service de validation
                /* } else {
                    // ResponseEntity => statut 400 + message d'erreur spécifique
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
                }*/

            } else {
                // ResponseEntity => statut 404 + message d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Transaction with id " + id);
            }

        } catch (Exception e){

            // ResponseEntity pris en charge par SpringBoot => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction edit)");
        }
    }

    /*-----------------Créer une nouvelle transaction-----------------*/
    @PostMapping("/transaction")
    // La method retourne => Type ReponseEntity
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction newTransaction) {
        Transaction savedTransaction;

        // ma logique
        try {
            // j'enregistre la nouvelle transaction en BDD + stockage dans variable
            savedTransaction = transactionRepository.save(newTransaction);

            // ResponseEntity => envoi d'un statut 201 + variable
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);

        } catch(Exception e){

            // ResponseEntity pris en charge par SpringBoot => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction creation)");
        }
    }

    /*--------------Supprimer une transaction par son Id---------------*/
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction;
    
        // ma logique
        try {
            // je récupère la transaction (optional renvoie un boolean)
            optionalTransaction = transactionRepository.findById(id);

            // si j'obtient true
            if(optionalTransaction.isPresent()){

                // je stocke la transaction dans ma variable
                Transaction transactionToDelete = optionalTransaction.get();
                // je supprime la transaction
                transactionRepository.delete(transactionToDelete);
                // ResponseEntity => envoi d'un statut 204 + message d'erreur
                return ResponseEntity.ok("The transaction " + id + " has been deleted");

            } else {
                // ResponseEntity => statut 404 + message d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Transaction with id" + id);
            }
        } catch (Exception e) {
            // EXCEPTION (ResponseEntity pris en charge par SpringBoot) => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction delete)", e);
        }
    }

}

