package com.banking;

import com.banking.model.Account;
import com.banking.model.City;
import com.banking.model.Customer;
import com.banking.repository.AccountRepository;
import com.banking.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {



		private final AccountRepository accountRepository;
		private final CustomerRepository customerRepository;

	public BankingSystemApplication(AccountRepository accountRepository, CustomerRepository customerRepository) {
			this.accountRepository = accountRepository;
			this.customerRepository = customerRepository;
		}
	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class,args);
	}

		@Override
		public void run (String...args) throws Exception {
			Customer c1 = Customer.builder()
					.id("1234568")
					.name("Samir")
					.city(City.Lankaran)
					.address("Ev")
					.dateOfBirth(1995)
					.build();
			Customer c2 = Customer.builder()
					.id("789456")
					.name("Rashad")
					.city(City.Lankaran)
					.address("Ev")
					.dateOfBirth(2000)
					.build();
			Customer c3 = Customer.builder()
					.id("456238")
					.name("Faride")
					.city(City.Lankaran)
					.address("Ev")
					.dateOfBirth(2005)
					.build();

			customerRepository.saveAll(Arrays.asList(c1, c2, c3));

			Account a1 = Account.builder()
					.id("100")
					.customerId("1234568")
					.city(City.Baku)
					.balance(1320.0)
					.build();
			Account a2 = Account.builder()
					.id("101")
					.customerId("789456")
					.city(City.Baku)
					.balance(7898.0)
					.build();
			Account a3 = Account.builder()
					.id("102")
					.customerId("456238")
					.city(City.Baku)
					.balance(120000.0)
					.build();

			accountRepository.saveAll(Arrays.asList(a1, a2, a3));

		}
	}


