<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "ITCAST网络图书商城");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	<%--bar1.add("分类管理", "查看分类", "<c:url value='/adminjsps/admin/category/list.jsp'/>", "body");--%>
	bar1.add("分类管理", "查看分类", "<c:url value='/adminCategory?method=showCategoryList'/>", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	<%--bar1.add("图书管理", "查看图书", "<c:url value='/adminjsps/admin/book/list.jsp'/>", "body");--%>
	bar1.add("图书管理", "查看图书", "<c:url value='/adminBook?method=showBookList'/>", "body");
	<%--bar1.add("图书管理", "添加图书", "<c:url value='/adminjsps/admin/book/add.jsp'/>", "body");--%>
	bar1.add("图书管理", "添加图书", "<c:url value='/adminBook?method=loadAddBookCg'/>", "body");

	bar1.add("订单管理", "所有订单", "<c:url value='/adminOrder?method=myOrders&state=9999'/>", "body");
	bar1.add("订单管理", "未付款订单", "<c:url value='/adminOrder?method=myOrders&state=0'/>", "body");
	bar1.add("订单管理", "已付款订单", "<c:url value='/adminOrder?method=myOrders&state=1'/>", "body");
	bar1.add("订单管理", "未收货订单", "<c:url value='/adminOrder?method=myOrders&state=2'/>", "body");
	bar1.add("订单管理", "已完成订单", "<c:url value='/adminOrder?method=myOrders&state=3'/>", "body");

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">

<c:if test="${sessionadmin != null}">
	<div id="menu"></div>
</c:if>



</body>
</html>
