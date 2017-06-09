<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/17/2016
  Time: 18:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%
  response.sendRedirect(request.getContextPath()+"/bookServlet?method=getBooks");
%>
