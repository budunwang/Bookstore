package com.wjx.bookstore.dao;

import com.wjx.bookstore.domain.Trade;

import java.util.Set;

/**
 * Created by admin on 12/18/2016.
 */
public interface TradeDao {
    /**
     * 向数据表中插入 Trade 对象
     * @param trade
     */
    public abstract void insert(Trade trade);

    /**
     * 根据 userId 获取和其关联的 Trade 的集合
     * @param userId
     * @return
     */
    public abstract Set<Trade> getTradesWithUserId(Integer userId);
}
