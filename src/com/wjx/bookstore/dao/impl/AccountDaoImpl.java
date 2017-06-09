package com.wjx.bookstore.dao.impl;


import com.wjx.bookstore.dao.AccountDao;
import com.wjx.bookstore.db.JDBCUtils;
import com.wjx.bookstore.domain.Account;

import java.sql.Connection;

/**
 * Created by admin on 1/5/2017.
 */
public class AccountDaoImpl extends BaseDao<Account> implements AccountDao{
    @Override
    public Account get(Integer accountId) {
        String sql="SELECT accountId, balance FROM account WHERE accountId = ?";
        return query(sql,accountId);
    }

    @Override
    public void updateBalance(Integer accountId, float amount) {
        String sql="UPDATE account SET balance = balance - ? WHERE accountId = ?";
        update(sql,amount,accountId);
    }
}
