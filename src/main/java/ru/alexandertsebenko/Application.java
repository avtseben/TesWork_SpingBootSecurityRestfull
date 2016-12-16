package ru.alexandertsebenko;

import ru.alexandertsebenko.datamodel.Account;
import ru.alexandertsebenko.db.AccountRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class);
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {

	return new CommandLineRunner() {

	    @Override
	    public void run(String... arg0) throws Exception {
		//Создаём первого пользователя-администратора(Доступны все CRUD операции)
		accountRepository.save(new Account("admin","adminpass","ADMIN"));
		//Создаём первого пользователя(Доступны READ операции)
		accountRepository.save(new Account("user","userpass","USER"));
	    }

	};

    }
}
