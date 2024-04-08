package ram.dev.bankingApp.service.impl;

import org.springframework.stereotype.Service;
import ram.dev.bankingApp.dto.AccountDto;
import ram.dev.bankingApp.mapper.AccountMapper;
import ram.dev.bankingApp.model.Account;
import ram.dev.bankingApp.repository.AccountRepository;
import ram.dev.bankingApp.service.AccountService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Does Not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Does Not exist"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
       Account savedAccount= accountRepository.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account Does Not exist"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient Balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }
}
