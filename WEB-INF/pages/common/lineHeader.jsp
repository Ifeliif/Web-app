<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/define.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" type="text/css" href="css/style.css" />


</head>
<body class="header1">


	<h1>路線管理</h1>


	<div style="border-style: solid; border-width: 1px; height: 120px">
	<a href="LoginConfirmservlet" type="text/html">
	<img src="css/images/GUM11_CL11043.jpg" alt="pic1" width="160" height="90" ></a>

		<div id="item1" style="height: 100px; width: 600px">

<table class="header" border="1">
		<tr>
		<td style="width: 70px; "><a href="BusListServlet"
		type="text/html">バス管理</a></td>
		<td style="width: 70px; "><a href="LineListServlet"
		type="text/html">路線管理</a></td>
		<td style="width: 70px; "><a href="MembersListServlet" type="text/html"
		>会員管理</a></td>
		</tr>


		</table></div>
		<a href="LogoutServlet" type="text/html">
		<input id="logout" type="submit" value="ログアウト"></a></div>
<div id="error-message" style="color: red;">
	<c:out value="${oneTimeMessage}"></c:out>
	<div id="errorArea">
		<c:forEach items="${errorMessages}" var="errorMessage">
			<c:out value="${errorMessage}"/><br/>
		</c:forEach>
	</div>
</div>