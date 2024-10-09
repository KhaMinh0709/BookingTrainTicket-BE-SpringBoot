package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.Account;
import com.DevJavaMinh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByRole(Role role);
}
