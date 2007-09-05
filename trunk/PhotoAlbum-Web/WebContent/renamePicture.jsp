<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/RenamePicture.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<html>
<head>
<title>renamePicture</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<% String pic = (String)request.getParameter("pic"); 
if (pic == null){
	pic = (String)session.getAttribute("pic");
}else{
	session.setAttribute("pic",pic);
	}
	String[] picArr = pic.split("[\\\\ \\.]");
	 
%>

	<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px; color: fuchsia;">Фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr></table>
	<table cellspacing="0" cellpadding="5" border="1" bgcolor="#FFcf66" align="center" bordercolor="ff0000">
	<form method="POST" action="RenamePictureServlet">
	<tr><td>Името в момента е:</td><td align="center"><%= picArr[picArr.length-2] %></td></tr>
	<tr><td>Новото име ще е :</td><td><input type="text" name="newName"></td></tr>
	<tr><td><input type="submit" name="submit" value="Преименувай"></td>
	
	
	</tr>
	</form>
	</table>

	</body>








</html>
