package org.microservice.repositories;

import jakarta.transaction.Transactional;
import org.microservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountName(String accountName);

    @Query(value = "Select COUNT(*) FROM account WHERE accountName = ?1", nativeQuery = true)
    Optional<Integer> countByAccountName(String accountName);

    @Query(value = "Select COUNT(*) FROM account WHERE email = ?1", nativeQuery = true)
    Optional<Integer> countByEmail(String email);
}
