package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.CustomerDto;
import com.DevJavaMinh.model.Customer;

public class CustomerMapping {
    public static Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setFullName(customerDto.getFullName());
        customer.setAddress(customerDto.getAddress());
        // set user sau luc create hoac update
        return customer;
    }
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getAccount().getAccount_id(),
                customer.getFullName(),
                customer.getAddress()
        );
    }
}
