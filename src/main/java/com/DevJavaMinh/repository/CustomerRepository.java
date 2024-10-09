package com.DevJavaMinh.repository;

import com.DevJavaMinh.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
