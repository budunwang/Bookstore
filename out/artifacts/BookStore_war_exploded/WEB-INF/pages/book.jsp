<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/23/2016
  Time: 23:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="script/jquery-3.1.1.min.js"></script>
    <%@ include file="/commons/queryCondition.jsp" %>
</head>
<body>

<br /><br />
Title:${book.title};
<br /><br />
Author:${book.author};
<br /><br />
Price:${book.price};
<br /><br />
PublishingDate:${book.publishingDate}
<br /><br />
Remark:${book.remark}
<br /><br />
<a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">Back to list</a>

</body>
</html>
