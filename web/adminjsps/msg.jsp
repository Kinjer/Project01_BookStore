<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'mgs.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  <body>
<h2>${msg }</h2>
<ul>
<%--<c:forEach items="${links }" var="link">--%>
	<%--<li>${link }</li>--%>
<%--</c:forEach>--%>

	<li><a href="<c:url value='/adminjsps/admin/index.jsp'/>">主页</a></li>
	<li><a href="<c:url value='/adminjsps/login.jsp'/>">登录</a></li>
	<li><a href="<c:url value='http://www.baidu.com'/>">百度一下</a></li>
</ul>
  </body>
</html>
