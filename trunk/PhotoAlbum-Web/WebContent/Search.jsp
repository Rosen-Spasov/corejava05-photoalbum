<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Search</title>
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
	<table cellspacing="0" cellpadding="5" border="1" bgcolor="#FFcf66"
	align="center" bordercolor="ff0000">
	<form method="POST" action="SearchPhotoServlet">
	
	<tr>
		<td>Въведете име или част от име на желаната снимка :</td>
		<td><input type="text" name="searchName"></td>
	</tr>
	<% if (session.getAttribute("errors")!=null){
	String[] errors = (String[])session.getAttribute("errors");
	if (errors != null){
			for (int k=0; k<errors.length;k++){
				if (errors[k]!=null){
	%>
	<tr><td colspan="2"><%= errors[k] %></td></tr>
	<%} } } }%>
	<tr>
		<td colspan="2" align="center"><input type="submit" name="submit" value="Търси" ></td>


	</tr>
	</form>
</table>

</body>
</html>
