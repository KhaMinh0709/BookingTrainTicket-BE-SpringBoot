package com.DevJavaMinh.service;

import com.DevJavaMinh.dto.AccountDto;
import com.DevJavaMinh.model.Role;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccountById(Long id, AccountDto accountDto);
    AccountDto getAccountById(Long id);
    void deleteAccountById(Long id);
    List<AccountDto> getAllAccounts();
    List<AccountDto> getAccountsByRole(Role role);
}
