package com.myBudget.myBudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.myBudget.myBudget.models")
public class MyBudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBudgetApplication.class, args);
	}

}
