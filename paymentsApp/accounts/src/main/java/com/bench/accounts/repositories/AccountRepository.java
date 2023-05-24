package com.bench.accounts.repositories;

import com.bench.accounts.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    //Busco por número de Account
    Optional<Account> findByNumber(String number);

    //Busco lista de Accounts mayores a X balance
    List<Account> findByBalanceGreaterThan(double balance);

    //Busco lista de Accounts que hayan sido creadas antes de X fecha
    List<Account> findByCreationDateBefore(LocalDateTime creationDate);

    List<Account> findByClientId(Long clientId);
}
