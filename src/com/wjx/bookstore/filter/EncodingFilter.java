package com.wjx.bookstore.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by admin on 12/30/2016.
 */
public class EncodingFilter implements Filter {

    private  FilterConfig filterConfig=null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding=filterConfig.getServletContext().getInitParameter("encoding");
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
