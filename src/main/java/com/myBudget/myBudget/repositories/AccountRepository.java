package com.myBudget.myBudget.repositories;

import com.myBudget.myBudget.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByClientId(Integer clientId);
}
