package com.wjx.bookstore.dao.impl;

import com.wjx.bookstore.dao.TradeItemDao;
import com.wjx.bookstore.domain.TradeItem;

import java.util.*;

/**
 * Created by admin on 1/6/2017.
 */
public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao{
    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql="INSERT INTO tradeitem(bookid, quantity, tradeid) VALUES (?, ?, ?)";
        Object [][] params=null;
        params=new Object[items.size()][3];
        List<TradeItem> tradeItemList=new ArrayList<TradeItem>(items);
        for(int i=0;i<items.size();i++) {
            params[i][0]=tradeItemList.get(i).getBookId();
            params[i][1]=tradeItemList.get(i).getQuantity();
            params[i][2]=tradeItemList.get(i).getTradeId();
        }
        batch(sql,params);
    }

    @Override
    public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
        String sql="SELECT itemId tradeItemId, bookId, quantity, tradeId FROM tradeitem "
                +"WHERE tradeId = ?";
        return new HashSet<>(queryForList(sql,tradeId));
    }
}
