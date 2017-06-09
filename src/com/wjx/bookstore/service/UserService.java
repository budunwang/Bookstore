package com.wjx.bookstore.service;

import com.wjx.bookstore.dao.BookDao;
import com.wjx.bookstore.dao.TradeDao;
import com.wjx.bookstore.dao.TradeItemDao;
import com.wjx.bookstore.dao.UserDao;
import com.wjx.bookstore.dao.impl.BookDaoImpl;
import com.wjx.bookstore.dao.impl.TradeDaoImpl;
import com.wjx.bookstore.dao.impl.TradeItemDaoImpl;
import com.wjx.bookstore.dao.impl.UserDaoImpl;
import com.wjx.bookstore.domain.Book;
import com.wjx.bookstore.domain.Trade;
import com.wjx.bookstore.domain.TradeItem;
import com.wjx.bookstore.domain.User;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by admin on 1/5/2017.
 */
public class UserService {

    private UserDao userDao=new UserDaoImpl();
    private TradeDao tradeDao=new TradeDaoImpl();
    private TradeItemDao tradeItemDao=new TradeItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    //根据用户名获得用户
    public User getUserByUserName(String username) {
        return userDao.getUser(username);
    }

    //根据用户名获得用户和交易信息
    public User getUserWithTradesByUserName(String username) {
        User user=userDao.getUser(username);
        if(user==null) {
            return null;
        }
        //获得交易信息
        int userId=user.getUserId();
        Set<Trade> trades=tradeDao.getTradesWithUserId(userId);

        if(trades!=null) {
            Iterator<Trade> iterator=trades.iterator();
            while(iterator.hasNext()) {
                Trade trade=iterator.next();
                int tradeId=trade.getTradeId();
                Set<TradeItem> tradeItems=tradeItemDao.getTradeItemsWithTradeId(tradeId);

                if(tradeItems!=null) {
                    for(TradeItem tradeItem:tradeItems) {
                        Book book=bookDao.getBook(tradeItem.getBookId());
                        tradeItem.setBook(book);
                    }
                }
                if(tradeItems.size()!=0) {
                    trade.setItems(tradeItems);
                } else {
                    iterator.remove();
                }
            }
        }

        if(trades.size()!=0) {
            user.setTrades(trades);
        }
        return user;
    }
}
