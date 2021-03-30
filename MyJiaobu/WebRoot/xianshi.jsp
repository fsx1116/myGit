<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
	<script>
    function shanchu(id){
   	 alert(id);
				var d=window.confirm("是否要删除");
				if(d){
					$.post(
							"${pageContext.request.contextPath}/shanchu",
							"id="+id,
							function(data){
								alert(data);
								window.location.reload();
							}
					);
				}
		};
	</script>
  </head>

  <body>
  <div align="center">
    <h2>我的脚印</h2>
    <table border="1">
        <tr>
            <td>序号</td>
            <td>国家/地区</td>
            <td>时间</td>
            <td>所见感想</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${jio}" var="Jb">
            <tr>
                <td>${Jb.id}</td>
                <td>${Jb.area}</td>
                <td>${Jb.time}</td>
                <td>${Jb.felling}</td>
                <td><input type="button"   onclick="shanchu(${Jb.id })" value="删除" ></td>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.request.contextPath }/tianjia.jsp">添加脚步</a>

  </div>
  </body>
</html>
