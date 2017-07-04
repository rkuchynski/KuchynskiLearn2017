package com.epam.mentoring.homework.banking;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.repository.embedded.api.IAccountRepository;
import com.epam.mentoring.homework.banking.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

/**
 * Test class for {@link AccountService}.
 * <p/>
 * Date: 06/30/2017
 *
 * @author Raman Kuchynski
 */
@RunWith(PowerMockRunner.class)
public class AccountServiceTest {

    private static final long ACCOUNT_ID = 1L;
    private static final long USER_ID = 1L;
    private static final double AMOUNT = 1000.0;

    private IAccountRepository accountRepository;
    private AccountService accountService;
    private Account account;

    @Before
    public void init() {
        accountRepository = PowerMock.createMock(IAccountRepository.class);
        accountService = new AccountService();
        Whitebox.setInternalState(accountService, "accountRepository", accountRepository);
        account = createAccount();
    }

    @Test
    public void testRead() {
        expect(accountRepository.read(ACCOUNT_ID)).andReturn(account).once();
        replayAll();
        Account result = accountService.read(ACCOUNT_ID);
        verifyAll();
        assertEquals(account, result);
    }

    @Test
    public void testReadIfNotFound() {
        expect(accountRepository.read(ACCOUNT_ID)).andReturn(null).once();
        replayAll();
        Account result = accountService.read(ACCOUNT_ID);
        verifyAll();
        assertNull(result);
    }

    @Test
    public void testStore() {
        expect(accountRepository.store(account)).andReturn(account).once();
        replayAll();
        Account result = accountService.store(account);
        verifyAll();
        assertEquals(account, result);
    }

    @Test
    public void testUpdate() {
        expect(accountRepository.update(account)).andReturn(account).once();
        replayAll();
        Account result = accountService.update(account);
        verifyAll();
        assertEquals(account, result);
    }

    @Test
    public void testDelete() {
        expect(accountRepository.delete(ACCOUNT_ID)).andReturn(account).once();
        replayAll();
        Account result = accountService.delete(ACCOUNT_ID);
        verifyAll();
        assertEquals(account, result);
    }

    private Account createAccount() {
        Account newAccount = new Account();
        newAccount.setAccountId(ACCOUNT_ID);
        newAccount.setUserId(USER_ID);
        newAccount.setAmount(AMOUNT);
        return newAccount;
    }
}
