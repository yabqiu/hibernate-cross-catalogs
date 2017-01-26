package service;

import config.AppConfig;
import entity.Account;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.Utils;

import javax.inject.Inject;

import static org.fest.assertions.Assertions.assertThat;
import static util.Client.client1;
import static util.Client.client2;
import static util.Client.mart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Ignore
public class AccountServiceTest {

    @Inject
    private AccountService accountService;

    @Test
    public void testFetchAccountFromMartScheme() {
        Account account = accountService.findById(Utils.getClass(Account.class, mart), 0);
        System.out.println(account);
        assertThat(account.getId()).isEqualTo(0);
    }


    @Test
    public void testFetchAccountFromClient1Scheme() {
        Account account = accountService.findById(Utils.getClass(Account.class, client1), 1);
        System.out.println(account);
        assertThat(account.getId()).isEqualTo(1);
    }

    @Test
    public void testFetchAccountFromClient2Scheme() {
        Account account = accountService.findById(Utils.getClass(Account.class, client2), 2);
        System.out.println(account);
        assertThat(account.getId()).isEqualTo(2);
    }

}
