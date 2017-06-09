package com.wjx.bookstore.dao;

import com.wjx.bookstore.domain.User;

/**
 * Created by admin on 12/18/2016.
 */
public interface UserDao {
    /**
     * 根据用户名获取 User 对象
     * @param username
     * @return
     */
    public abstract User getUser(String username);
}
