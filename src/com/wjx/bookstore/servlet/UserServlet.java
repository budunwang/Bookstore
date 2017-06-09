package com.wjx.bookstore.servlet;

import com.wjx.bookstore.domain.User;
import com.wjx.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 1/7/2017.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {

    private UserService userService=new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        User user=userService.getUserWithTradesByUserName(username);

        if(user==null) {
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
            return;
        }
        request.setAttribute("user",user);

        request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request,response);
    }
}
