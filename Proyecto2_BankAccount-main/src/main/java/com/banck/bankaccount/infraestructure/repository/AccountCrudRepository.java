package com.banck.bankaccount.infraestructure.repository;

import com.banck.bankaccount.domain.Account;
import com.banck.bankaccount.infraestructure.model.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.banck.bankaccount.aplication.model.AccountRepository;

/**
 *
 * @author jonavcar
 */
@Slf4j
@Component
public class AccountCrudRepository implements AccountRepository {

    @Autowired
    IAccountCrudRepository accountRepository;

    @Override
    public Mono<Account> get(String account) {
        log.info("AccountCrudRepository.get");
        return accountRepository.findById(account).map(this::AccountDaoToAccount);
    }

    public Account AccountDaoToAccount(AccountDao ad) {
        log.info("AccountCrudRepository.AccountDaoToAccount");
        Account account = new Account();
        account.setAccount(ad.getAccount());
        account.setCustomer(ad.getCustomer());
        account.setCustomerType(ad.getCustomerType());
        account.setAccountType(ad.getAccountType());
        account.setDateCreated(ad.getDateCreated());
        return account;
    }

    @Override
    public Flux<Account> listAll() {
        log.info("AccountCrudRepository.listAll");
        return accountRepository.findAll().map(this::AccountDaoToAccount);
    }

    @Override
    public Mono<Account> create(Account c) {
        log.info("AccountCrudRepository.create");
        Flux<AccountDao> fa = accountRepository.findAllByCustomer(c.getCustomer());
        return accountRepository.save(AccountToAccountDao(c)).map(this::AccountDaoToAccount);
    }

    @Override
    public Mono<Account> update(String account, Account c) {
        log.info("AccountCrudRepository.update");
        c.setAccount(account);
        return accountRepository.save(AccountToAccountDao(c)).map(this::AccountDaoToAccount);
    }

    @Override
    public void delete(String account) {
        log.info("AccountCrudRepository.delete");
        accountRepository.deleteById(account).subscribe();
    }

    public AccountDao AccountToAccountDao(Account c) {
        log.info("AccountCrudRepository.AccountToAccountDao");
        AccountDao creditDao = new AccountDao();
        creditDao.setAccount(c.getAccount());
        creditDao.setCustomer(c.getCustomer());
        creditDao.setCustomerType(c.getCustomerType());
        creditDao.setAccountType(c.getAccountType());
        creditDao.setDateCreated(c.getDateCreated());
        return creditDao;
    }

    @Override
    public Flux<Account> listAccountByCustomer(String customer) {
        log.info("AccountCrudRepository.listAccountByCustomer");
        return accountRepository.findAllByCustomer(customer).map(this::AccountDaoToAccount);
    }
    

}
