package com.wjx.bookstore.service;

import com.wjx.bookstore.dao.*;
import com.wjx.bookstore.dao.impl.*;
import com.wjx.bookstore.domain.*;
import com.wjx.bookstore.web.CriteriaBook;
import com.wjx.bookstore.web.Page;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by admin on 12/20/2016.
 */
public class BookService {

    private BookDao bookDao=new BookDaoImpl();
    private AccountDao accountDao=new AccountDaoImpl();
    private TradeDao tradeDao=new TradeDaoImpl();
    private UserDao userDao=new UserDaoImpl();
    private TradeItemDao tradeItemDao=new TradeItemDaoImpl();


    public Page<Book> getPage(CriteriaBook criteriaBook) {
        return bookDao.getPage(criteriaBook);
    }

    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    //添加书到购物车
    public boolean addInCart(int id, ShoppingCart shoppingCart) {
        Book book=bookDao.getBook(id);
        if(book==null) {
            return false;
        } else {
            shoppingCart.addBook(book);
            return true;
        }
    }
    //从购物车删除书
    public void deleteItemFromCart(int id,ShoppingCart shoppingCart){
        shoppingCart.removeItem(id);
    }
    //从购物车删除所有车，清空购物车
    public void deleteAllItemFromCart(ShoppingCart shoppingCart){
        shoppingCart.clear();
    }

    public void updateItemQuantity(int id, int quantity, ShoppingCart shoppingCart) {
        shoppingCart.updateItemQuantity(id,quantity);
    }

    //结账
    public void cash(ShoppingCart shoppingCart, String username, String accountId) {
        //更新数据库中的mybooks的书库存量
        bookDao.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
        //更新数据库中的account的balance余额
        accountDao.updateBalance(Integer.parseInt(accountId),shoppingCart.getTotalMoney());
        //在trade中插入交易数据
        Trade trade=new Trade();
        trade.setUserId(userDao.getUser(username).getUserId());
        trade.setTradeTime(new Date(new java.util.Date().getTime()));
        tradeDao.insert(trade);
        //在tradeItem中插入n条数据记录
        Collection<TradeItem> tradeItems=new ArrayList<TradeItem>();
        for(ShoppingCartItem shoppingCartItem:shoppingCart.getItems()) {
            TradeItem tradeItem=new TradeItem();
            tradeItem.setBookId(shoppingCartItem.getBook().getId());
            tradeItem.setQuantity(shoppingCartItem.getQuantity());
            tradeItem.setTradeId(trade.getTradeId());
            tradeItems.add(tradeItem);
        }
        tradeItemDao.batchSave(tradeItems);
        //清空购物车
        shoppingCart.clear();
    }
}
