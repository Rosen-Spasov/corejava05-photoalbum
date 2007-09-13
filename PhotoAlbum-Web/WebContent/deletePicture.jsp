<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/DeletePicture.java" --%><%-- /jsf:pagecode --%>
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
<title>deletePicture</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
</head>

	<body>
	<% User user = (User)session.getAttribute("user"); 
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
						path = edit.getAbsolutePath(ph);
	%>
	<table border="0" width="100%" cellspacing="0" cellpadding="0" style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-image:url(Themes/headBG.png)" >
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style=" background-image: Themes/headBG.png; text-align:center; border-right:  " >
			<span style="font-size: 30px; color: fuchsia;">Фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr></table>
	<table align="center">
	<tr><td><img src="<%= path%>" alt="<%= ph.getPhName() %>" title="<%= ph.getPhName() %>" align="top" width="400"/>
</td></tr>

<tr><td><a href="DeletePictureServlet?pic=<%= ph.getPhotoId() %>" onclick="close"/><b><div style="color: red;"><b><%= "Сигурен ли сте, че искате да изтриете тази снимка" %></div></b></a></td></tr>
<br><br>
<% } %>
</table>
	<%} } }%>
	</body>

</html>
