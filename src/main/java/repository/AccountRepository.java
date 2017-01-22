package repository;

import entity.Account;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class AccountRepository {

    @Inject
    private EntityManager entityManager;

    public <A extends Account> A findById(Class<A> clazz, Integer id) {
        return entityManager.find(clazz, id);
    }

    public void saveAccount(Account account) {
        entityManager.persist(account);
    }
}
