package com.banck.bankaccount.aplication.impl;

import com.banck.bankaccount.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.banck.bankaccount.aplication.AccountOperations;
import com.banck.bankaccount.aplication.model.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jonavcar
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountOperationsImpl implements AccountOperations {

    Logger logger = LoggerFactory.getLogger(AccountOperationsImpl.class);
    private final AccountRepository accountRepository;

    @Override
    public Flux<Account> list() {
        log.info("AccountOperationsImpl.list");
        return accountRepository.listAll();
    }

    @Override
    public Mono<Account> get(String credito) {
        log.info("AccountOperationsImpl.get");
        return accountRepository.get(credito);
    }

    @Override
    public Mono<Account> create(Account account) {
        log.info("AccountOperationsImpl.create");
        return accountRepository.create(account);
    }

    @Override
    public Mono<Account> update(String credito, Account c) {
        log.info("AccountOperationsImpl.update");
        return accountRepository.update(credito, c);
    }

    @Override
    public void delete(String credito) {
        log.info("AccountOperationsImpl.delete");
        accountRepository.delete(credito);
    }

    @Override
    public Flux<Account> listAccountByCustomer(String customer) {
        log.info("AccountOperationsImpl.listAccountByCustomer");
        return accountRepository.listAccountByCustomer(customer);
    }

}
