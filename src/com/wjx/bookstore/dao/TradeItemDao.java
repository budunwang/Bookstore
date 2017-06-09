package com.wjx.bookstore.dao;

import com.wjx.bookstore.domain.TradeItem;

import java.util.Collection;
import java.util.Set;

/**
 * Created by admin on 12/18/2016.
 */
public interface TradeItemDao {
    /**
     * 批量保存 TradeItem 对象
     * @param items
     */
    public abstract void batchSave(Collection<TradeItem> items);

    /**
     * 根据 tradeId 获取和其关联的 TradeItem 的集合
     * @param tradeId
     * @return
     */
    public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);
}
