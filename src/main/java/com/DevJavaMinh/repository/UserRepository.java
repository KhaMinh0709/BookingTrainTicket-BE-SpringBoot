package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.User;
import com.DevJavaMinh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
}
