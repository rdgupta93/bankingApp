package ram.dev.bankingApp.service;

import ram.dev.bankingApp.dto.AccountDto;
import ram.dev.bankingApp.model.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id,double amount);
    List<AccountDto>getAllAccounts();
}
