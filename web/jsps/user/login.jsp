<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <link href="${pageContext.request.contextPath}/jquery/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/jquery/bootstrap/css/sigin.css" rel="stylesheet">


</head>

<body>


<%
    String userName = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = URLDecoder.decode(cookie.getValue(), "utf-8");
            } else if (cookie.getName().equals("pwd")) {
                password = URLDecoder.decode(cookie.getValue(), "utf-8");
            }
        }
    }
%>


<%--<h1>登录</h1>--%>

<%--<form action="<c:url value='/user'/>" method="post">--%>
<%--<input type="hidden" name="method" value="login"/>--%>
<%--用户名：<input type="text" name="username" value="<%=userName%>"/><br/>--%>
<%--密　码：<input type="password" name="password" value="<%=password%>"/><br/>--%>
<%--<input type="submit" value="登录"/>--%>
<%--</form>--%>

<div class="container">

    <form class="form-signin" action="<c:url value='/user'/>" method="post">
        <input type="hidden" name="method" value="login"/>
        <h2 class="form-signin-heading">Please login</h2>
        <p style="color: red; font-weight: 900">${msg }</p>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus
               name="username" value="<%=userName%>">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
               name="password" value="<%=password%>">
        <div class="checkbox">
            <label>
                <input type="checkbox" value="re" name="rememberMe"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/bootstrap/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/jquery/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
