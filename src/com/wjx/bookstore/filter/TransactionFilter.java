package com.wjx.bookstore.filter;

import com.wjx.bookstore.db.JDBCUtils;
import com.wjx.bookstore.web.ConnectionContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by admin on 1/7/2017.
 */
@WebFilter(filterName = "TransactionFilter")
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Connection connection=null;
        try {
            //获取连接
            connection= JDBCUtils.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            //利用ThreadLocal将connection与当前线程绑定
            ConnectionContext.getInstance().bind(connection);
            //请求传递
            chain.doFilter(req, resp);
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //回滚事务
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            //重定向到错误页面error-1.jsp
            HttpServletRequest request=(HttpServletRequest) req;
            HttpServletResponse response=(HttpServletResponse) resp;
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
        } finally {
            //解除Connection绑定
            ConnectionContext.getInstance().remove();
            //关闭连接
            JDBCUtils.release(connection);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
