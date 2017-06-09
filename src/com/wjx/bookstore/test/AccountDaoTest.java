package com.wjx.bookstore.test;

import com.wjx.bookstore.dao.impl.AccountDaoImpl;
import com.wjx.bookstore.domain.Account;
import org.junit.Test;

/**
 * Created by admin on 1/5/2017.
 */
public class AccountDaoTest {
    public AccountDaoImpl accountDaoImpl=new AccountDaoImpl();

    @Test
    public void TestGet() {
        Account account=accountDaoImpl.get(1);
        System.out.print(account.getBalance());
    }

    @Test
    public void TestUpdateBalance() {
        accountDaoImpl.updateBalance(1,20);
    }
}
