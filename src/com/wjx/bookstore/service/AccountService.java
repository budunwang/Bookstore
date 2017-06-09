package com.wjx.bookstore.service;

import com.wjx.bookstore.dao.AccountDao;
import com.wjx.bookstore.dao.impl.AccountDaoImpl;
import com.wjx.bookstore.domain.Account;

/**
 * Created by admin on 1/5/2017.
 */
public class AccountService {

    private AccountDao accountDao=new AccountDaoImpl();

    //根据账户id获取账户
    public Account getAccount(int accountId) {
        return accountDao.get(accountId);
    }
}
