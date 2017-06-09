package com.wjx.bookstore.test;

import com.sun.xml.internal.ws.spi.db.RepeatedElementBridge;
import com.wjx.bookstore.dao.BookDao;
import com.wjx.bookstore.domain.Book;
import com.wjx.bookstore.domain.ShoppingCartItem;
import org.junit.Test;
import com.wjx.bookstore.dao.impl.BookDaoImpl;
import com.wjx.bookstore.web.CriteriaBook;
import com.wjx.bookstore.web.Page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by admin on 12/18/2016.
 */
public class BookDaoTest {

    private BookDao bookDao=new BookDaoImpl();

    @Test
    public void TestGetBook() {
        Book book=bookDao.getBook(3);
        System.out.println(book);
    }

    @Test
    public void TestGetPage() {
        CriteriaBook cb=new CriteriaBook(50,60,90);
        Page<Book> page=bookDao.getPage(cb);
        System.out.println("pageNo: " + page.getPageNo());
        System.out.println("totalPageNumber: " + page.getTotalPageNumber());
        System.out.println("list: " + page.getList());
        System.out.println("prevPage: " + page.getPrevPage());
        System.out.println("nextPage: " + page.getNextPage());
    }

    @Test
    public void TestGetStoreNumber() {
        int storeNumber=bookDao.getStoreNumber(5);
        System.out.println(storeNumber);
    }

    @Test
    public void TestBatchUpdateStoreNumberAndSalesAmount() {
        Collection<ShoppingCartItem> items=new ArrayList<ShoppingCartItem>();

        Book book=bookDao.getBook(1);
        ShoppingCartItem shoppingCartItem=new ShoppingCartItem(book);
        shoppingCartItem.setQuantity(10);
        items.add(shoppingCartItem);

        book=bookDao.getBook(5);
        shoppingCartItem=new ShoppingCartItem(book);
        shoppingCartItem.setQuantity(1);
        items.add(shoppingCartItem);

        book=bookDao.getBook(6);
        shoppingCartItem=new ShoppingCartItem(book);
        shoppingCartItem.setQuantity(2);
        items.add(shoppingCartItem);

        bookDao.batchUpdateStoreNumberAndSalesAmount(items);
    }
}
