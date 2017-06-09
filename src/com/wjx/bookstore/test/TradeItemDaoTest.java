package com.wjx.bookstore.test;

import com.wjx.bookstore.dao.TradeItemDao;
import com.wjx.bookstore.dao.impl.TradeItemDaoImpl;
import com.wjx.bookstore.domain.TradeItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by admin on 1/6/2017.
 */
public class TradeItemDaoTest {
    private TradeItemDao tradeItemDao=new TradeItemDaoImpl();

    @Test
    public void TestBatchSave() {
        Collection<TradeItem> items=new ArrayList<>();
        items.add(new TradeItem(null,1,5,17));
        items.add(new TradeItem(null,2,6,17));
        items.add(new TradeItem(null,3,7,17));
        tradeItemDao.batchSave(items);
    }

    @Test
    public void TestGetTradeItemsWithTradeId() {
        Set<TradeItem> tradeItems=tradeItemDao.getTradeItemsWithTradeId(12);
        for(TradeItem tradeItem:tradeItems) {
            System.out.println(tradeItem);
        }
    }
}
