package com.wjx.bookstore.web;

import com.wjx.bookstore.domain.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 12/29/2016.
 */
public class BookStoreWebUtils {
    //get shopping cart from session
    public static ShoppingCart getShoppingCart(HttpServletRequest request) {
        HttpSession session=request.getSession();
        ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("ShoppingCart");
        if(shoppingCart==null) {
            shoppingCart=new ShoppingCart();
            session.setAttribute("ShoppingCart",shoppingCart);
        }
        return shoppingCart;
    }
}
