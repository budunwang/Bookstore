<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/7/2017
  Time: 4:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>User: ${user.username}</h4>
<table>

    <c:forEach items="${user.trades}" var="trade">
        <tr>
            <td>
                <table border="1" cellpadding="10" cellspacing="0">
                    <tr>
                        <td colspan="3">TradeTime: ${trade.tradeTime}</td>
                    </tr>

                    <c:forEach items="${trade.items}" var="tradeItem">
                        <tr>
                            <td>${tradeItem.book.title}</td>
                            <td>${tradeItem.book.price}</td>
                            <td>${tradeItem.quantity}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
