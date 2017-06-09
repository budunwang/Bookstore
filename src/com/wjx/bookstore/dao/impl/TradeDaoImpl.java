package com.wjx.bookstore.dao.impl;

import com.wjx.bookstore.dao.TradeDao;
import com.wjx.bookstore.domain.Trade;

import java.util.*;

/**
 * Created by admin on 1/6/2017.
 */
public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao{
    @Override
    public void insert(Trade trade) {
        String sql="INSERT INTO trade (userid,tradetime) VALUES (?,?)";
        long tradeId=insert(sql,trade.getUserId(),trade.getTradeTime());
        trade.setTradeId((int)tradeId);
    }

    @Override
    public Set<Trade> getTradesWithUserId(Integer userId) {
        String sql="SELECT tradeId, userId, tradeTime FROM trade WHERE userId = ? ORDER BY tradeTime DESC";
        return new LinkedHashSet<>(queryForList(sql,userId));
    }
}
