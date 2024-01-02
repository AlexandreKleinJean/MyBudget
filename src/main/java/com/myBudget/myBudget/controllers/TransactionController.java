package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Transaction;
import com.myBudget.myBudget.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;



@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    /*-------------Afficher les transactions par compte-------------*/
    @GetMapping("/{accountId}/transactions")
    public Iterable<Transaction> getAllTransactions(@PathVariable Integer accountId) {
        Iterable<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        // ResponseEntity est géré automatiquement avec "findAll()"
        return transactions;
    }

    /*------------Afficher une transaction par son Id-------------*/
    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        // ResponseEntity est géré automatiquement avec "findById()"
        return optionalTransaction.orElse(null);
    }    

    /*-----------------Créer une nouvelle transaction-----------------*/
    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(Transaction newTransaction) {

        // J'enregistre la nouvelle transaction en BDD
        Transaction savedTransaction = transactionRepository.save(newTransaction);

        // J'envoi un statut 201 + les infos de la nouvelle transaction
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    /*----------Modifier les infos d'une transaction par son Id---------*/
    @PatchMapping("/transaction/{id}")
    public Transaction updateTransaction(@PathVariable Integer id, Transaction updatedTransaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        return optionalTransaction.map(transaction -> {
            // Je remplace les infos de la transac' existante par les infos de la nouvelle
            transaction.setSubject(updatedTransaction.getSubject());
            transaction.setNote(updatedTransaction.getNote());
            transaction.setIcon(updatedTransaction.getIcon());
            transaction.setDate(updatedTransaction.getDate());
            transaction.setCategory(updatedTransaction.getCategory());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setAccountId(updatedTransaction.getAccountId());
    
            // J'enregistre les infos en BDD
            transactionRepository.save(transaction);
    
            return transaction;
        }).orElse(null);
    }

    /*--------------Supprimer une transaction par son Id---------------*/
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transactionRepository.delete(transaction);

            // Suppression ok => j'envoi un statut 204
            return ResponseEntity.noContent().build();
        } else {
            // Transaction non trouvée => j'envoi un statut 404
            return ResponseEntity.notFound().build();
        }
    }
}
