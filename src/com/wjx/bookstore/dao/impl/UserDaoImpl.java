package com.wjx.bookstore.dao.impl;

import com.wjx.bookstore.dao.UserDao;
import com.wjx.bookstore.domain.User;

/**
 * Created by admin on 1/5/2017.
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUser(String username) {
        String sql="SELECT userId, username, accountId FROM userinfo WHERE username = ?";
        return query(sql,username);
    }
}
