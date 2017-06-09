package com.wjx.bookstore.dao;

import com.wjx.bookstore.domain.Account;

/**
 * Created by admin on 12/18/2016.
 */
public interface AccountDao {
    /**
     * 根据 accountId 获取对应的 Account 对象
     * @param accountId
     * @return
     */
    public abstract Account get(Integer accountId);

    /**
     * 根据传入的 accountId, amount 更新指定账户的余额: 扣除 amount 指定的钱数
     * @param accountId
     * @param amount
     */
    public abstract void updateBalance(Integer accountId, float amount);
}
