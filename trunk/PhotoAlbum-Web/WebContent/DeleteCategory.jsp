<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/DeleteCategory.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<html>
<head>
<title>DeleteCategory</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">

</head>

	<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px; color: fuchsia;">Фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr></table>
	<% String categ = (String) session.getAttribute("currentCategory"); %>
	
	<div style="color: red;font-size: 40px;" align="center"><b>Сигурен ли сте, че искате да изтриете категория <u><%= categ %></u>
	 и всички снимки, които съдържа </b>
	<br>
	<a href="DeleteCategoryServlet">Да</a>
	<a href="ShowUser.jsp">Не</a>
	</div>
	</body>

</html>
