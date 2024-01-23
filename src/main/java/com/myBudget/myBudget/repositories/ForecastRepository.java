package com.myBudget.myBudget.repositories;

import com.myBudget.myBudget.models.Forecast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
}
