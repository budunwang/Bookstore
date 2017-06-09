<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/5/2017
  Time: 20:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
Book Number: ${sessionScope.ShoppingCart.bookNumber}
<br /><br />
Total Price: ${sessionScope.ShoppingCart.totalMoney}
<br /><br />

<c:if test="${requestScope.errors!=null}">
    <font color="red">${requestScope.errors}</font>
</c:if>

<form action="bookServlet?method=cash" method="post">
    <table>
        <tr>
            <td>Credit Card Username</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>Credit Card AccountId</td>
            <td><input type="text" name="accountId" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="submit"></td>
        </tr>
    </table>

</form>


</body>
</html>
