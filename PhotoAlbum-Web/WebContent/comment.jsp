<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/FullScreen.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.Photo"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.Set"%>
<%@page import="photoalbum.PhotoAlbumManipulator"%>
<html>
<head>
</head>
<body>

<% if (request.getParameter("pic")!=null){
	int photoId = Integer.parseInt((String)request.getParameter("pic"));
		String path = null;
		User user = (User)session.getAttribute("user"); 
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		Set<Category> category = user.getCategories();
		for (Category categ: category){
			Set<Photo> photos = categ.getPhotos();
				for (Photo ph: photos){
					if(ph.getPhotoId()==photoId){
						session.setAttribute("pic",ph);
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
	<td class="tabsTableMiddle" ><div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic= <%= ph.getPhotoId()%>"><img src="<%= path %>" alt="<%= ph.getPhName() %>" title="<%= ph.getPhName() %>" height="150px"/></a>
	</div>
		<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=<%= ph.getPhotoId() %>" class="link bold">Виж в цял размер</a>
		<%= ph.getPhName()  %>
	</div>
	<form method="GET" action="CommentServlet">
	<div class="lh17">Коментари <span class="bold"><%= ph.getComments().size() %></span></div>
	<tr><td><textarea name="comment" cols="30" rows="4" />
     </textarea></td></tr>
	<tr><td><input type="submit" name="button"	value="Submit" /></td> </tr>
	</form></table>
<% }}} }%>

</body>
</html>