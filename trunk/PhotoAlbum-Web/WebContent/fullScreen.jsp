<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/FullScreen.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@page import="photoalbum.entities.Photo"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="java.util.Set"%>

<%@page import="photoalbum.entities.Comment"%>
<%@page import="photoalbum.PhotoAlbumManipulator"%>
<html>
<head>

<title>fullScreen</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
</head>

	<body>
	<% 	boolean isComment = false;
		User userLogin = (User)session.getAttribute("login"); 
		User user = (User)session.getAttribute("user"); 
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		if (user != null){
		String allPictures = (String) session.getAttribute("allPictures");
		int photoId = Integer.parseInt((String)request.getParameter("pic"));
			String path = null;
					Set<Category> category = user.getCategories();					
					for (Category categ: category){
					Set<Photo> photos = categ.getPhotos();
					for (Photo ph: photos){
						if(ph.getPhotoId()==photoId){
						path = edit.getAbsolutePath(ph);%>
					
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px; color: fuchsia;">Фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr></table>
	<table align="center">
	<tr><td><img src="<%= path%>" alt="<%= ph.getPhName() %>" title="<%= ph.getPhName() %>" align="top"/>
</td></tr>
<%Set<Comment> comm = ph.getComments(); 
	for (Comment c: comm){
	isComment = true; 
%>
<tr><td><div style="color: red;"><%= "Този коментар е оставен от " %><b><%=c.getUser().getUsername() %></div></td></tr>
<tr><td><%= c.getText() %></td></tr><br><br>
<% } %>
</table>
	<%break;
						}
					 } }
					
	if (!isComment){
		if (userLogin != null){%>
	<tr><td><a href="comment.jsp?pic=<%= photoId %>"<div style="color: red;"><b><%= "Няма коментари искаш ли да направиш първия " %></div></td></tr>
	<% }}else{
		if (userLogin != null){%>
		<tr><td><a href="comment.jsp?pic=<%= photoId %>"<div style="color: blue;"><b><%= "Желаете ли да добавите коментар " %></div></td></tr>
	<%}}}else{%>
	<tr><td><a href="MainPage"<div style="color: red;"><b><%= "Трябва да сте регистриран потребител за да видите тази снимка " %></div></td></tr>
	<%}%>
	</body>

</html>
