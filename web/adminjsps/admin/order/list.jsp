<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px rgb(78,78,78);
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body style="background: rgb(254,238,189);">
<h1>我的订单</h1>
<p style="font-weight: 900; color: red">${sendFlag}</p>

<c:if test="${sendFlag == null}">

<table border="1" width="100%" cellspacing="0" background="black">
	<%--<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">--%>
		<%--<td colspan="6">--%>
			<%--订单：8691b4150a0641e7a8729fd5e668820c　成交时间：2013-06-04 15:56:53　金额：<font color="red"><b>126.4</b></font>	已收货（完成）--%>
		<%--</td>--%>
	<%--</tr>--%>
	<%--<tr bordercolor="rgb(78,78,78)" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/20385925-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Struts2深入详解</td>--%>
		<%--<td>单价：63.2元</td>--%>
		<%--<td>作者：孙鑫</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：126.4元</td>--%>
	<%--</tr>--%>
  <%----%>
 <%----%>



		<c:forEach var="myOrd" items="${myOrderList}">
			<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
				<td colspan="6">
					订单编号：${myOrd.oid}　成交时间：${myOrd.ordertime}　金额：<font color="red"><b>${myOrd.price}</b></font>　

					<c:choose>
						<c:when test="${myOrd.state == 0}">
							未付款
						</c:when>
						<c:when test="${myOrd.state == 1}">
							已付款|<a href="<c:url value='/adminOrder?method=sendGoods&oid=${myOrd.oid}'/>">发货</a>
						</c:when>
						<c:when test="${myOrd.state == 2}">
							已发货
						</c:when>
						<c:when test="${myOrd.state == 3}">
							已收货（完成）
						</c:when>
					</c:choose>

				</td>
			</tr>

			<c:forEach var="myOrderItem" items="${myOrd.orderItemList}">
				<tr bordercolor="rgb(78,78,78)" align="center">
					<td width="15%">
						<div><img src="<c:url value='${myOrderItem.image}'/>" height="75"/></div>
					</td>
					<td>书名：${myOrderItem.bname}</td>
					<td>单价：${myOrderItem.price}元</td>
					<td>作者：${myOrderItem.author}</td>
					<td>数量：${myOrderItem.count}</td>
					<td>小计：${myOrderItem.subtotal}元</td>
				</tr>
			</c:forEach>
		</c:forEach>

</table>
</c:if>
  </body>
</html>
