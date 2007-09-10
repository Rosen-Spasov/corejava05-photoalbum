<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.Photo"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.Set"%>
<html>
<head>
</head>
<body>
<% String picPath = (String) request.getParameter("picPath");


User user = (User)session.getAttribute("user"); 
	
	Photo photo = new Photo();
	Set<Category> category = user.getCategories();
	out.println(picPath);
 %>

 
</body>
</html>