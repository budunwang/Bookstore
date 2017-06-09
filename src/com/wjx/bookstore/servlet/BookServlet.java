package com.wjx.bookstore.servlet;

import com.google.gson.Gson;
import com.wjx.bookstore.domain.*;
import com.wjx.bookstore.service.AccountService;
import com.wjx.bookstore.service.BookService;
import com.wjx.bookstore.service.UserService;
import com.wjx.bookstore.web.BookStoreWebUtils;
import com.wjx.bookstore.web.CriteriaBook;
import com.wjx.bookstore.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 12/20/2016.
 */

@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {

    private BookService bookService=new BookService();
    private UserService userService=new UserService();
    private AccountService accountService=new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //动态分配方法
        String methodName=request.getParameter("method");
        try {
            Method method=getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=-1;
        String idStr=request.getParameter("id");
        try {
            id=Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
        }

        Book book=null;
        if(id!=-1) {
            book=bookService.getBook(id);
        }

        //书不存在
        if(book==null) {
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
            return;
        }
        //书存在
        request.setAttribute("book",book);
        request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request,response);
    }

    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo=1;
        int minPrice=0;
        int maxPrice=Integer.MAX_VALUE;

        String pageNoStr=request.getParameter("pageNo");
        String minPriceStr=request.getParameter("minPrice");
        String maxPriceStr=request.getParameter("maxPrice");

        try {
            pageNo=Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {
        }

        try {
            minPrice=Integer.parseInt(minPriceStr);
        } catch (NumberFormatException e) {
        }

        try {
            maxPrice=Integer.parseInt(maxPriceStr);
        } catch (NumberFormatException e) {
        }

        CriteriaBook cb=new CriteriaBook(minPrice,maxPrice,pageNo);
        Page<Book> page=bookService.getPage(cb);

        request.setAttribute("bookpage",page);
        request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request,response);
    }

    //获取购物车
    protected void addInCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取书id
        String bookIdStr=request.getParameter("id");
        int bookId=-1;
        try {
            bookId=Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {}

        //获取购物车对象
        ShoppingCart shoppingCart= BookStoreWebUtils.getShoppingCart(request);

        //将书加入购物车对象
        boolean bookIsExist=false;
        bookIsExist=bookService.addInCart(bookId,shoppingCart);

        //显示getbooks页
        if(bookIsExist) {
            getBooks(request, response);
            return;
        } else {
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
            return;
        }
    }



    //从购物车删除书
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取书id
        String bookIdStr=request.getParameter("id");
        int bookId=-1;
        try {
            bookId=Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {}
        //获取购物车对象
        ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
        //删除购物车中的书
        bookService.deleteItemFromCart(bookId,shoppingCart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);
        return;
    }

    //从购物车删除所有书
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
        //删除购物车中的所有书
        bookService.deleteAllItemFromCart(shoppingCart);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);
        return;
    }

    //ajax动态修改购物车数据
    protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String idStr=request.getParameter("id");
        String quantityStr=request.getParameter("quantity");

        int id=-1;
        int quantity=-1;

        try {
            id=Integer.parseInt(idStr);
            quantity=Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {}

        ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
        //修改数据
        if(id>0&&quantity>0) {
            bookService.updateItemQuantity(id,quantity,shoppingCart);
        }
        //返回json数据
        Map<String,Object> result=new HashMap<String,Object>();
        result.put("bookNumber",shoppingCart.getBookNumber());
        result.put("totalMoney",shoppingCart.getTotalMoney());
        //封装为json数据
        Gson gson=new Gson();
        String jsonStr=gson.toJson(result);
        //返回数据
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }

    //前往购物车页
    /*
    protected void toCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request,response);
        return;
    }
    */

    //前往结账页
    /*
    protected void gotoCashPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request,response);
    }
    */

    //前往转发页
    protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        request.getRequestDispatcher("/WEB-INF/pages/"+page+".jsp").forward(request,response);
    }

    protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String accountId=request.getParameter("accountId");

        //验证表单是否符合基本规范
        StringBuffer errors=validateFormField(username,accountId);
        //表单字符非空
        if(errors.toString().equals("")) {
            errors=validateUser(username,accountId);
            //用户名与账户名相符
            if(errors.toString().equals("")) {
                errors=validateBookStoreNumber(request);
                //库存数充足
                if(errors.toString().equals("")) {
                    errors=validateBalance(request,accountId);
                }
            }
        }
        if(!errors.toString().equals("")) {
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request,response);
            return;
        }

        //通过验证结账操作
        bookService.cash(BookStoreWebUtils.getShoppingCart(request),username,accountId);
        response.sendRedirect(request.getContextPath()+"/success.jsp");
    }

    //验证表单是否符合基本规范：非空，数值类型等。不需要查询数据库。
    protected StringBuffer validateFormField(String username, String accountId) {
        StringBuffer errors=new StringBuffer();
        if(username==null||username.trim().equals("")) {
            errors.append("Username is null<br />");
        }
        if(accountId==null||accountId.trim().equals("")) {
            errors.append("AccountId is null!");
        }
        return errors;
    }

    //验证表单username和accountId是否匹配
    protected StringBuffer validateUser(String username,String accountId) {
        StringBuffer errors=new StringBuffer();
        Boolean flag=false;
        User user=userService.getUserByUserName(username);
        if(user!=null) {
            if (accountId.equals(user.getAccountId().toString())) {
                flag=true;
            }
        }
        if(!flag) {
            errors.append("Wrong username or accountId");
        }
        return errors;
    }

    //验证库存是否充足
    protected StringBuffer validateBookStoreNumber(HttpServletRequest request) {
        StringBuffer errors=new StringBuffer();
        ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);

        for(ShoppingCartItem shoppingCartItem:shoppingCart.getItems()) {
            //查询现在数据库中的书存量
            int bookId=shoppingCartItem.getBook().getId();
            int bookStoreNumber=bookService.getBook(bookId).getStoreNumber();
            int quantity=shoppingCartItem.getQuantity();
            if(quantity>bookStoreNumber) {
                errors.append(shoppingCartItem.getBook().getTitle()+" store number is not enough!<br />");
            }
        }
        return errors;
    }

    //验证用户账号余额是否充足
    protected StringBuffer validateBalance(HttpServletRequest request, String accountId) {
        StringBuffer errors=new StringBuffer();
        ShoppingCart shoppingCart=BookStoreWebUtils.getShoppingCart(request);
        Account account=accountService.getAccount(Integer.parseInt(accountId));

        if(shoppingCart.getTotalMoney()>account.getBalance()) {
            errors.append("The account balance is not enough!");
        }
        return errors;
    }

}
