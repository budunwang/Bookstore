package com.wjx.bookstore.test;

import com.wjx.bookstore.dao.TradeDao;
import com.wjx.bookstore.dao.impl.TradeDaoImpl;
import com.wjx.bookstore.domain.Trade;
import org.junit.Test;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by admin on 1/6/2017.
 */
public class TradeDaoTest {
    public TradeDao tradeDao=new TradeDaoImpl();

    @Test
    public void TestiInsert() {
        Trade trade=new Trade();
        trade.setUserId(3);
        trade.setTradeTime(new Date(new java.util.Date().getTime()));
        tradeDao.insert(trade);
    }

    @Test
    public void TestGetTradesWithUserId() {
        Set<Trade> trades=tradeDao.getTradesWithUserId(3);
        for(Trade trade:trades) {
            System.out.println(trade);
        }
    }
}
