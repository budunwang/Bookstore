package com.wjx.bookstore.test;

import com.wjx.bookstore.dao.UserDao;
import com.wjx.bookstore.dao.impl.UserDaoImpl;
import com.wjx.bookstore.domain.User;
import org.junit.Test;

/**
 * Created by admin on 1/5/2017.
 */
public class UserDaoTest {
    public UserDaoImpl userDaoImpl=new UserDaoImpl();

    @Test
    public void TestGetUser() {
        User user=userDaoImpl.getUser("AAA");
        System.out.println(user.getAccountId()+" "+user.getUserId());
    }
}
