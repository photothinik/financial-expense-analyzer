package org.photothinik.finance.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.photothinik.finance.expense.repository")
@EntityScan("com.photothinik.finance.expense.model")
@SpringBootApplication
public class ExpenseAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseAnalyzerApplication.class, args);
	}

}
