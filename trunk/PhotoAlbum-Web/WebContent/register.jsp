<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>register</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
<% String[] errorsRegistration = (String[])request.getAttribute("errorsRegistration"); 
	if (errorsRegistration == null){
	out.println("null");
	errorsRegistration = new String[6];
	for (int k=0;k<errorsRegistration.length;k++){
	errorsRegistration[k]="";
	}
	}
%><table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px;">Направете своята регистрация във фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr>

<tr><td></td><td>
<form method="POST" action="RegisterServlet">
	<table cellspacing="0" cellpadding="5" border="1" bgcolor="#FFcf66" align="left" bordercolor="ff0000">
		
			<tr><td>Въведи име</td><td><input type="text" name="fName" value=""></td><td>${errorsRegistration[1]}</td></tr>
			<tr><td>Въведи фамилия</td><td><input type="text" name="lName" value=""></td><td>${errorsRegistration[2]}</td></tr>
			<tr><td>enter user name</td><td><input type="text" name="uName" value=""></td><td>${errorsRegistration[3]}</td></tr>
			<tr><td>enter password</td><td><input type="password" name="pass"></td><td>${errorsRegistration[4]}</td></tr>
			<tr><td>confirm password</td><td><input type="password" name="confPass"></td><td>${errorsRegistration[5]}</td></tr>
		
			<tr><td width="150"><input type="submit" value="register"></td><td>${errorsRegistration[6]}</td></tr>
		
	</table></form>
	</table>
</body>
</html>
