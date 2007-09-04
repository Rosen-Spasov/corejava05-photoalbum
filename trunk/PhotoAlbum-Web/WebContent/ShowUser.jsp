<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.User"%>
<html>
<head>
<title>ShowUser</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
<table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px;">Направете своята регистрация във фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr>
<% String id = (String)request.getParameter("param1");%>

<% User user = (User)session.getAttribute("user"); 
	if (user==null){
	%>
	<tr><td></td><td><div style="color: red;">За съжаление няма намерен потребител с това име</div></td></tr>
	<tr><td></td><td><a href="MainPage.jsp">връщане в начална страница</a>
	<% }else{%>
	<tr><td></td><td>${user.FirstName()}</td></tr>
	<% }%>
	
</table>
</body>
</html>
