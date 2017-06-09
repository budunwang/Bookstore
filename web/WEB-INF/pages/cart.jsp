<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/29/2016
  Time: 20:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="script/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //删除确认
            $(".delete").click(function(){
                var title= $.trim($(this).parent().parent().find("td:first").text());
                var del=confirm("Delete "+title+" from cart?");
                if(del) {
                    return true;
                } else {
                    return false;
                }
            });
            //删除购物车确认
            $(".deleteAll").click(function(){
                var del=confirm("Delete all items from cart?");
                if(del) {
                    return true;
                } else {
                    return false;
                }
            });
            //ajax修改商品数量
            $(":text").change(function(){
                var quantityVal= $.trim(this.value);
                var reg=/^\d+$/g;
                var flag=false;
                var quantity=0;
                if(reg.test(quantityVal)) {
                    quantity=parseInt(quantityVal);
                    if(quantity>=0) {
                        flag=true;
                    }
                }
                if(!flag) {
                    alert("Illegal number!");
                    $(this).val($(this).attr("class"));
                    return;
                }
                if(quantity==0) {
                    var title= $.trim($(this).parent().parent().find("td:first").text());
                    var del=confirm("Delete "+title+" from cart?");
                    if(del) {
                        //获取链接节点
                        var $delhref=$(this).parent().parent().find("a");
                        //执行链接点击事件
                        $delhref[0].onclick();
                        return;
                    }
                }
                //设置确认框
                var $tr=$(this).parent().parent();
                var title= $.trim($tr.find("td:first").text());
                var flag=confirm("Revise the number of the "+title+" ?");
                if(!flag) {
                    $(this).val($(this).attr("class"));
                    return;
                }
                //设置ajax的url，json数据
                var url="bookServlet";
                var idVal= $.trim(this.name);

                var args={"method":"updateItemQuantity","id":idVal,"quantity":quantityVal,"time":new Date()};
                //实现ajax的post方法
                $.post(url,args,function(data){
                    var bookNumber=data.bookNumber;
                    var totalMoney=data.totalMoney;
                    //使用返回数据修改页面数据
                    $("#bookNumber").text("Book Number: "+bookNumber);
                    $("#totalMoney").text("Sum: "+totalMoney);
                },"JSON");
            });
        })
    </script>
    <%@ include file="/commons/queryCondition.jsp" %>
</head>
<body>
        <div id="bookNumber">
        Book Number: ${sessionScope.ShoppingCart.bookNumber}
        </div>
        <table>
            <tr>
                <td>Title</td>
                <td>Quantity</td>
                <td>Price</td>
                <td>&nbsp;</td>
            </tr>
        <c:forEach items="${sessionScope.ShoppingCart.items}" var="item">
            <tr>
                <td>${item.book.title}</td>
                <td>
                    <input type="text" size="1" value="${item.quantity}" class="${item.quantity }" name="${item.book.id}" />
                </td>
                <td>${item.book.price}</td>
                <td><a href="bookServlet?method=delete&pageNo=${param.pageNo}&id=${item.book.id}" class="delete">delete</a></td>
            </tr>
            <br />
        </c:forEach>
        <br /><br />
        <tr>
            <td colspan="4" id="totalMoney">Sum: ${sessionScope.ShoppingCart.totalMoney}</td>
        </tr>
        <br /><br />
        <tr>
            <td colspan="4">
                <a href="bookServlet?method=getBooks&pageNo=${param.pageNo}">Back to shopping list</a>
                <br />
                <a href="bookServlet?method=deleteAll" class="deleteAll">clear the chart</a>
                <br />
                <a href="bookServlet?method=forwardPage&page=cash">checkout</a>
                <br />
            </td>
        </tr>
        </table>

</body>
</html>
