package org.microservice.repositories;

import jakarta.transaction.Transactional;
import org.microservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountName(String accountName);
}
