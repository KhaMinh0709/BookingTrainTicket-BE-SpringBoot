package com.DevJavaMinh.mapper;

import com.DevJavaMinh.dto.AccountDto;
import com.DevJavaMinh.model.Account;

public class AccountMapping {
    public static Account mapToAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getAccount_id(),
                accountDto.getUsername(),
                accountDto.getPassword(),
                accountDto.getRole(),
                accountDto.getPhoneNumber(),
                accountDto.getEmail()
        );
    }
    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getAccount_id(),
                account.getUsername(),
                account.getPassword(),
                account.getRole(),
                account.getPhoneNumber(),
                account.getEmail()
        );
    }
}
