<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/RenamePicture.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="photoalbum.PhotoAlbumManipulator"%>
<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.Photo"%>
<html>
<head>
<title>renamePicture</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<body>
<% int photoId;
	if (request.getParameter("pic")!=null){
		photoId = Integer.parseInt((String)request.getParameter("pic"));
	
		User user = (User)session.getAttribute("user"); 
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String[] errors = (String[])session.getAttribute("errorsPicture");
		out.println(errors);
		if (user != null){
		
				Set<Category> category = user.getCategories();					
					for (Category categ: category){
					Set<Photo> photos = categ.getPhotos();
					for (Photo ph: photos){
						if(ph.getPhotoId()==photoId){
						String[] name = ph.getPhName().split("[.]");
						session.setAttribute("category",categ);
						session.setAttribute("photo",ph);
%>
<table border="0" width="100%" cellspacing="0" cellpadding="0"
	style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)">
		<td width="124" style="border-right:0px solid White;"><img
			src="Themes/top-left.gif"></td>
		<td
			style=" background-image: Themes/headBG.png; text-align:center; border-right:  ">
		<span style="font-size: 30px; color: fuchsia;">Фото албум</span></td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr>
</table>
<table cellspacing="0" cellpadding="5" border="1" bgcolor="#FFcf66"
	align="center" bordercolor="ff0000">
	<form method="POST" action="RenamePictureServlet">
	<tr>
		<td>Името в момента е:</td>
		<td align="center"><%= name[0]%></td>
	</tr>
	<tr>
		<td>Новото име ще е :</td>
		<td><input type="text" name="newName"></td>
	</tr>
	<% if (errors != null){
			for (int k=0; k<errors.length;k++){
				if (errors[k]!=null){
	%>
	<tr><td colspan="2"><%= errors[k] %></td></tr>
	<%}} }%>
	<tr>
		<td><input type="submit" name="submit" value="Преименувай"></td>


	</tr>
	</form>
</table>
<% } } } } }%>
</body>








</html>
