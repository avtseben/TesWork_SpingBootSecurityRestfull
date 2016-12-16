package ru.alexandertsebenko.db;

import java.util.List;

import ru.alexandertsebenko.datamodel.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

        public Account findByUsername(String username);
}
