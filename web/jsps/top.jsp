<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
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
		background: #4682B4; 
	}
	a {
		text-transform:none;
		text-decoration:none;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">LANOU书店</h1>
<div style="font-size: 10pt;">
		<%--您好：张三&nbsp;&nbsp;|&nbsp;&nbsp;--%>
			<c:if test="${sessionuser != null}">
				您好：${sessionuser} &nbsp;&nbsp;|&nbsp;&nbsp;

				<a href="<c:url value='/newCart?method=showCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<%--<a href="<c:url value='/jsps/order/list.jsp'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
				<a href="<c:url value='/order?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<%--<a href="javascript:alert('您已经退出');" target="_parent">退出</a>--%>
				<a href="<c:url value='/user?method=userExit'/>" target="_parent">退出</a>

			</c:if>
			<c:if test="${sessionuser == null}">
				未登录 &nbsp;&nbsp;|&nbsp;&nbsp;
			</c:if>
		<%--<a href="<c:url value='/newCart?method=showCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
		<%--&lt;%&ndash;<a href="<c:url value='/jsps/order/list.jsp'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;&ndash;%&gt;--%>
		<%--<a href="<c:url value='/order?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;--%>
		<%--&lt;%&ndash;<a href="javascript:alert('您已经退出');" target="_parent">退出</a>&ndash;%&gt;--%>
			<%--<a href="<c:url value='/user?method=userExit'/>" target="_parent">退出</a>--%>
		<br/>
		<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
		<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a> |&nbsp;
			<a href="<c:url value='/adminjsps/login.jsp'/>" target="_parent">管理员登录</a>
</div>
  </body>
</html>
