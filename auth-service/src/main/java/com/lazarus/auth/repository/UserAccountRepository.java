package com.lazarus.auth.repository;

import com.lazarus.auth.model.UserAccount;
import com.lazarus.auth.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByUsername(String username);

    List<UserAccount> findByRole(UserRole role);

    List<UserAccount> findByActive(Boolean active);

    boolean existsByUsername(String username);
}
