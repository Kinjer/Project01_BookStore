<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body>
  <c:if test="${delFlag == null}">
  <div>
    <img src="<c:url value='${bookInfo.image}'/>" width="131px" height="150px" border="0"/>
  </div>
  <%--<form style="margin:20px;" id="form" action="javascript:alert('操作成功！');" method="post">--%>
  <form style="margin:20px;" id="form" action="<c:url value="/adminBook"/>" method="post">
	  <input type="hidden" name="bid" value="${bookInfo.bid}">
  	图书名称：<input type="text" name="bname" value="${bookInfo.bname}"/><br/>
  	图书单价：<input type="text" name="price" value="${bookInfo.price}元"/><br/>
  	图书作者：<input type="text" name="author" value="${bookInfo.author}"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
     		<%--<option value="">JavaSE</option>--%>
    		<%--<option value="">JavaEE</option>--%>
			<%--<option value="">JavaScript</option>--%>
			<%--<option value="">Hibernate</option>--%>
			<%--<option value="">Struts</option>--%>
			<%--<option value="" selected='selected'>Spring</option>--%>
	  <c:forEach var="category" items="${categoryList}">
		  <option value="${category.cid}" <c:if test="${bookInfo.cid == category.cid}">selected="selected"</c:if> >
		  ${category.cname}
		  </option>
	  </c:forEach>

    	</select><br/>
  	<input type="submit" name="method" value="del" onclick="return confirm('是否真要删除该图书？');"/>
  	<input type="submit" name="method" value="mod"/>
  </form>
  </c:if>
<br><br>
  <p style="font-weight: 900; color: red">${msg }</p>
  </body>
</html>
