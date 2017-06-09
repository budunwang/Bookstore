<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/23/2016
  Time: 23:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {
        $("a").each(function(){
            this.onclick=function() {
                var serializeVal = $(":hidden").serialize();
                var href = this.href + "&" + serializeVal;
                window.location.href = href;
                return false;
            };
        });
    })
</script>

<input type="hidden" name="minPrice" value="${param.minPrice}" />
<input type="hidden" name="maxPrice" value="${param.maxPrice}" />

