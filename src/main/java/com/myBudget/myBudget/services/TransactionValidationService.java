package com.myBudget.myBudget.services;

import org.springframework.stereotype.Service;

import com.myBudget.myBudget.models.Account;
import com.myBudget.myBudget.repositories.AccountRepository;
import com.myBudget.myBudget.models.Transaction;

import java.math.BigDecimal;
/*import java.util.Date;
import java.text.SimpleDateFormat;*/

@Service
public class TransactionValidationService {

    private final AccountRepository accountRepository;

    public TransactionValidationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

/*-----------Methods de validation de chaque propriétes de transaction-----------*/

    public String validateSubject(String subject) {
        if (subject == null || !subject.matches("^[a-zA-Z ]+$")) {
            return "Subject can only contain letters and spaces";
        }
        return null;
    }

    public String validateNote(String note) {
        if (note.length() > 50) {
            return "Note length must be less than or equal to 50 characters";
        }
        return null;
    }

    /*public String validateDate(Date date) {
        // je configure le format à respecter (dateFormat) avec la Class SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // je compare la date entrée (date) avec le format (dateFormat)
        String formattedDate = dateFormat.format(date);

        if (!formattedDate.equals(date.toString())) {
            return "Date must be in the format dd/MM/yyyy";
        }
        return null; 
    }*/

    public String validateAmount(BigDecimal amount) {
        if (amount == null) {
            return "Amount must be provided";
        }
        
        return null;
    }

    public String validateAccountId(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account with ID " + accountId + " doesn't exist";
        }
        return null;
    }

/*-----------Methods regroupant toutes les methods de validation-----------*/

    public String validateTransaction(Transaction transaction) {
        String subjectValidation = validateSubject(transaction.getSubject());
        String noteValidation = validateNote(transaction.getNote());
        /*String dateValidation = validateDate(transaction.getDate());*/
        String amountValidation = validateAmount(transaction.getAmount());
        String accountIdValidation = validateAccountId(transaction.getAccountId());

        // je controle qu'aucune validation ne débouchent sur un message d'erreur
        if (subjectValidation != null) {
            return subjectValidation;
        } else if (noteValidation != null) {
            return noteValidation;
        /*}else if (dateValidation != null) {
            return dateValidation;*/
        }else if (amountValidation != null) {
            return amountValidation;
        }else if (accountIdValidation != null) {
            return accountIdValidation;
        } else {
            return null;
        }
    }
}


