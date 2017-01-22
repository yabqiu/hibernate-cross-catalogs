package service;

import entity.Account;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

import javax.inject.Inject;

@Service
public class AccountService {

    @Inject
    private AccountRepository accountRepository;

    public <A extends Account> A findById(Class<A> clazz, Integer id) {
        return accountRepository.findById(clazz, id);
    }
}
