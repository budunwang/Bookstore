package com.wjx.bookstore.dao.impl;

import com.wjx.bookstore.dao.BookDao;
import com.wjx.bookstore.domain.Book;
import com.wjx.bookstore.domain.ShoppingCartItem;
import com.wjx.bookstore.web.CriteriaBook;
import com.wjx.bookstore.web.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 12/18/2016.
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    @Override
    public Book getBook(int id) {
        String sql="SELECT id, author, title, price, publishingDate, " +
                "salesAmount, storeNumber, remark FROM mybooks WHERE id = ?";
        return query(sql,id);
    }

    @Override
    public Page<Book> getPage(CriteriaBook cb) {
        Page page=new Page<>(cb.getPageNo());

        //设置当前页列表
        page.setTotalItemNumber(getTotalBookNumber(cb));
        //校验pageNo是否越界
        cb.setPageNo(page.getPageNo());
        page.setList(getPageList(cb,3));
        return page;
    }

    @Override
    public long getTotalBookNumber(CriteriaBook cb) {
        String sql="SELECT count(id) FROM mybooks WHERE price>=? AND price<=?";
        return getSingleVal(sql,cb.getMinPrice(),cb.getMaxPrice());
    }

    @Override
    public List<Book> getPageList(CriteriaBook cb, int pageSize) {
        String sql = "SELECT id, author, title, price, publishingDate, " +
                "salesAmount, storeNumber, remark FROM mybooks " +
                "WHERE price >= ? AND price <= ? LIMIT ?,?";
        int start=(cb.getPageNo()-1)*pageSize;
        return queryForList(sql,cb.getMinPrice(),cb.getMaxPrice(),start,pageSize);
    }

    @Override
    public int getStoreNumber(Integer id) {
        String sql="SELECT storeNumber FROM mybooks WHERE id=?";
        return getSingleVal(sql,id);
    }

    @Override
    public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql="UPDATE mybooks SET salesAmount = salesAmount + ?, storeNumber = storeNumber - ? "
                +"WHERE id = ?";
        Object [][] params=null;
        params=new Object[items.size()][3];
        List<ShoppingCartItem> shoppingCartItemList=new ArrayList<ShoppingCartItem>(items);
        for(int i=0;i<items.size();i++) {
            params[i][0]=shoppingCartItemList.get(i).getQuantity();
            params[i][1]=shoppingCartItemList.get(i).getQuantity();
            params[i][2]=shoppingCartItemList.get(i).getBook().getId();
        }
        batch(sql,params);
    }
}
