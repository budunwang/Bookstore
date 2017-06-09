package com.wjx.bookstore.test;

import com.wjx.bookstore.domain.Book;
import org.junit.Test;
import com.wjx.bookstore.dao.impl.BookDaoImpl;

import java.sql.Date;
import java.util.List;

/**
 * Created by admin on 12/18/2016.
 */
public class BaseDaoTest {
        private BookDaoImpl bookDaoImpl=new BookDaoImpl();
        @Test
        public void TestInsert() {
                String sql="INSERT INTO trade (userid,tradetime) VALUES (?,?)";
                long id=bookDaoImpl.insert(sql,2,new Date(new java.util.Date().getTime()));
                System.out.println(id);
        }

        @Test
        public void TestUpdate() {
                String sql="UPDATE mybooks SET salesamount=? where id=?";
                bookDaoImpl.update(sql,10,4);
        }

        @Test
        public void TestQuery() {
                String sql="SELECT id,author,title,price,publishingDate,"+
                        "salesAmount,storeNumber,remark FROM mybooks where id=?";
                Book book=bookDaoImpl.query(sql,4);
                System.out.println(book);
        }

        @Test
        public void TestQueryForList() {
                String sql="SELECT id,author,title,price,publishingDate,"+
                        "salesAmount,storeNumber,remark FROM mybooks where id<?";
                List<Book> books=bookDaoImpl.queryForList(sql,3);
                System.out.println(books);
        }

        @Test
        public void TestGetSingleVal() {
                String sql="SELECT count(id) FROM mybooks";
                long count=bookDaoImpl.getSingleVal(sql);
                System.out.println(count);
        }

        @Test
        public void  TestBatch() {
                String sql = "UPDATE mybooks SET salesamount = ?, storenumber = ? " +
                        "WHERE id = ?";

                bookDaoImpl.batch(sql, new Object[]{2, 2, 1}, new Object[]{2, 2, 2}, new Object[]{3, 3, 3});
        }
}
