package com.DevJavaMinh.service.imp;

import com.DevJavaMinh.dto.AccountDto;
import com.DevJavaMinh.exception.NotFoundException;
import com.DevJavaMinh.mapper.AccountMapping;
import com.DevJavaMinh.model.Account;
import com.DevJavaMinh.model.Role;
import com.DevJavaMinh.repository.AccountRepository;
import com.DevJavaMinh.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapping.mapToAccount(accountDto);
        Account accountSave = accountRepository.save(account);
        return AccountMapping.mapToAccountDto(accountSave);
    }

    @Override
    public AccountDto updateAccountById(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));

        account.setAccount_id(accountDto.getAccount_id());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setRole(accountDto.getRole());
        accountRepository.save(account);
        return AccountMapping.mapToAccountDto(account);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));
        return AccountMapping.mapToAccountDto(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream().map(AccountMapping::mapToAccountDto).toList();
    }

    @Override
    public List<AccountDto> getAccountsByRole(Role role) {
        List<Account> list = accountRepository.findByRole(role);
        return list.stream().map(AccountMapping::mapToAccountDto).toList();
    }
}
