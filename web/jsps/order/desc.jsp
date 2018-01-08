<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#pay {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -412px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#pay:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -448px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>

	  <%--<script type="text/javascript" src="../../jquery/jquery-3.2.1.js"></script>--%>
	  <script type="text/javascript">

		  function payOrder() {
			  var text = document.getElementById("address_input").value;
			  if(text == "") {
				  alert("请填写收货地址!");
			  }else {
				  document.getElementById("pay_form").submit();
			  }
		  }

	  </script>

  </head>
  
  <body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			<%--订单编号：123456　成交时间：2000-01-01 15:30　金额：<font color="red"><b>319.2元</b></font>--%>
			订单编号：${orderInfo.oid}　成交时间：${orderInfo.ordertime}　金额：<font color="red"><b>${orderInfo.price}元</b></font>
		</td>
	</tr>

	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：Java详解</td>--%>
		<%--<td>单价：39.9元</td>--%>
		<%--<td>作者：张孝祥</td>--%>
		<%--<td>数量：2</td>--%>
		<%--<td>小计：79.8元</td>--%>
	<%--</tr>--%>
<%--<c:forEach var="cartMap" items="${orderInfo.cart.cartMap}">--%>
	<%--<tr bordercolor="gray" align="center">--%>
		<%--<td width="15%">--%>
			<%--<div><img src="<c:url value='/${cartMap.value.bookBean.image}'/>" height="75"/></div>--%>
		<%--</td>--%>
		<%--<td>书名：${cartMap.value.bookBean.bname}</td>--%>
		<%--<td>单价：${cartMap.value.bookBean.price}元</td>--%>
		<%--<td>作者：${cartMap.value.bookBean.author}</td>--%>
		<%--<td>数量：${cartMap.value.count}</td>--%>
		<%--<td>小计：${cartMap.value.bookBean.price * cartMap.value.count}元</td>--%>
	<%--</tr>--%>
<%--</c:forEach>--%>

	<c:forEach var="oItem" items="${orderInfo.orderItemList}">
		<tr bordercolor="gray" align="center">
			<td width="15%">
				<div><img src="<c:url value='/${oItem.image}'/>" height="75"/></div>
			</td>
			<td>书名：${oItem.bname}</td>
			<td>单价：${oItem.price}元</td>
			<td>作者：${oItem.author}</td>
			<td>数量：${oItem.count}</td>
			<td>小计：${oItem.subtotal}元</td>
		</tr>
	</c:forEach>

</table>
<br/>
<%--<form method="post" action="javascript:alert('别点了，再点就去银行页面了！');" id="form" target="_parent">--%>
<form method="post" action="<c:url value="/order"/>" id="pay_form" target="_parent">
	<input type="hidden" name="method" value="payment">
	<input type="hidden" name="oid" value="${orderInfo.oid}">
	<input type="hidden" name="price" value="${orderInfo.price}">
	收货地址：<input id="address_input" type="text" name="address" size="50" value=""/><br/>

	选择银行：<br/>
	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
	<img src="bank_img/icbc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
	<img src="bank_img/bc.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
	<img src="bank_img/abc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
	<img src="bank_img/ccb.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
	<img src="bank_img/bcc.bmp" align="middle"/><br/>
</form>
<%--<a id="pay" href="javascript:document.getElementById('pay_form').submit();"></a>--%>
<a id="pay" href="javascript:void(0)" onclick="payOrder()"></a>


  </body>
</html>

