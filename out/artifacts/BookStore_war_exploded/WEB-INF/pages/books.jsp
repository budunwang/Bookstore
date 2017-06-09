<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/20/2016
  Time: 21:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="CONTENT-TYPE" content="text/html;charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="script/jquery-3.1.1.min.js"></script>

    <script type="text/javascript">
        $(function(){
            $("#pageNo").change(function(){
                var val=$(this).val();
                //去掉空格
                val= $.trim(val);
                //检验是否是数字
                //检验是否超过总页数
                var reg=/^\d+$/g;
                var flag=false;
                var pageNo=0;
                if(reg.test(val)) {
                    pageNo=parseInt(val);
                    if(pageNo>1&&pageNo<parseInt("${bookpage.totalPageNumber}")) {
                        flag = true;
                    }
                }
                if(!flag) {
                    alert("Illegal Page Number");
                    $(this).val("");
                    return;
                } else {
                    var href="bookServlet?method=getBooks&pageNo="+pageNo+"&"+$(":hidden").serialize();
                    window.location.href=href;
                }
            })
        })
    </script>
    <%@ include file="/commons/queryCondition.jsp" %>
</head>
<body>

<c:if test="${param.title!=null}">
    ${param.title} is added into the cart！
    <br /><br />
</c:if>

<c:if test="${!empty sessionScope.ShoppingCart.books}">
    Your shopping cart: ${sessionScope.ShoppingCart.bookNumber} books <a href="bookServlet?method=forwardPage&page=cart&pageNo=${bookpage.pageNo}">check</a>
</c:if>
<form action="bookServlet?method=getBooks" method="post">
    Price:
    <input type="text" size="1" name="minPrice" />
    -
    <input type="text" size="1" name="maxPrice" />
    <input type="submit" value="submit">
</form>
<br /><br />
<table>
    <c:forEach items="${bookpage.list}" var="book">
        <tr>
            <td>
                <a href="bookServlet?method=getBook&pageNo=${bookpage.pageNo}&id=${book.id}">${book.title}</a>
                <br />
                ${book.author}
            </td>
            <td>${book.price}</td>
            <td><a href="bookServlet?method=addInCart&pageNo=${bookpage.pageNo}&id=${book.id}&title=${book.title}">Add in cart</a></td>
        </tr>
    </c:forEach>
</table>
<br /><br />
Total ${bookpage.totalPageNumber} pages
&nbsp;&nbsp;
Now ${bookpage.pageNo} page
&nbsp;&nbsp;
<c:if test="${bookpage.hasPrev}">
    <a href="bookServlet?method=getBooks&pageNo=1">First Page</a>
    &nbsp;&nbsp;
    <a href="bookServlet?method=getBooks&pageNo=${bookpage.prevPage}">Previous Page</a>
    &nbsp;&nbsp;
</c:if>
<c:if test="${bookpage.hasNext}">
    <a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage}">Next Page</a>
    &nbsp;&nbsp;
    <a href="bookServlet?method=getBooks&pageNo=${bookpage.totalPageNumber}">Last Page</a>
    &nbsp;&nbsp;
</c:if>

&nbsp;&nbsp;
Goto <input type="text" size="1" id="pageNo" /> Page

</body>
</html>
