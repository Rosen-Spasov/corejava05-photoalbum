<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.Photo"%>
<%@page import="photoalbum.entities.Comment"%>
<%@page import="bean.WebBean"%>
<html>
<link rel="stylesheet" type="text/css" href="Themes/showUser.css" />

		
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
			<span style="font-size: 30px; color: fuchsia;">Фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr></table>
<% User userLogin = (User)session.getAttribute("login"); %>
<% User user = (User)session.getAttribute("user"); 
	if (user == null){
	
	%>
	<table class="main_table" align="center" cellpadding="0" cellspacing="0" >
	<tr><td></td><td><div style="color: red;">За съжаление няма намерен потребител с това име</div></td></tr>
	<tr><td></td><td><a href="MainPage.jsp">връщане в начална страница</a>
	<% }else{%>
	<tr><td></td><td></td></tr>
	<tr><td></td><td style="" align="center"><div style="font-size: 20px; color: maroon; background-color: aqua" align="center"> <%= "Това е профила на "+user.getFirstName()+ " "+ user.getLastName() %></div></td></tr>
						<tr>
				<td colspan="2" style="width: 950px; height: 21px; padding:0; margin:0; background-color: white;" class="">
						<table class="mainTabsTable" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td class="mainMenu">
				<div class="mainTab startTab selectedStart">
					<img src="http://www.sibir.bg/style/img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers?param=vs" title="Всички снимки">Всички снимки</a></span>
					<img src="http://www.sibir.bg/style/img/tr.gif" class="tr" alt="tr" />
				</div>
				<div class="mainTab photoTab">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="Search.jsp" title="Търсене">Търсене</a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				<%if (user.equals(userLogin)){
			%>
				<div class="mainTab blogsTab">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers" title="Категория">Добави категория</a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				
				<div class="mainTab searchTab">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="DeleteCategory.jsp" title="Категория">Изтрии категория</a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				<div class="mainTab profileTab">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers" title="Категория">Преименувай категория</a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				
				<%} %>
			<%if (userLogin == null){
			%>
			
			<div class="sideMenuLinks"><a class="grey" href="register.jsp" title="Регистрация в Sibir.bg">Регистрация</a></div>
			<div class="sideMenuLinks"><a class="grey" href="MainPage.jsp" title="Вход в photo album">Вход</a><span class="separator grey">|</span></div>
			<% }else{ %>
			<div class="sideMenuLinks"><a class="grey" href="ExitServlet" title="Регистрация в Sibir.bg">Излез</a></div>
			<% } %>
			<div class="sideMenuLinks"><a class="grey" href="MainPage.jsp" title="Вход в photo album">Начална страница</a><span class="separator grey">|</span></div>	
				</td></tr>
				<% 
				Set<Category> category = user.getCategories();
				String[] type = {"mainTab searchTab","mainTab profileTab","mainTab messagesTab","mainTab invTab",
				"mainTab startTab selectedStart","mainTab videoTab","mainTab photoTab","mainTab groupsTab"};
				
				 %>
				<tr>
		<td class="mainMenu">	
				<% int t=1;%>
				<div class="<%= type[0] %>">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers?allPictures=allPictures" title="allPictures">Виж всички снимки</a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				<%for (Category categ : category){
				
				%>
				<div class="<%= type[t] %>">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers?param=<%= categ %>" title="<%= categ %>"><%= categ %></a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
			<%t++;} %>
			
		</td>
	</tr>
</table>				</td>
			</tr>
	<% }%>
	<table class="tabsMiddle top10" cellpadding="2" cellspacing="0" align="center">
	<tr>
		<td class="tabs">
			<div class="rightTabStub">&nbsp;</div>
		
			
			<div class="leftTabStubMiddle">&nbsp;</div>
		</td>
	</tr>
	
	
		<tr><div class="smallestProfile" >
					<%String firstPath = WebBean.firstPath;
					int row = 0;
					int pages = 1;
					String allPictures = (String) session.getAttribute("allPictures");
					String path = null;
					if (allPictures != null){
					Set<Category> category = user.getCategories();
					for (Category categ: category){
					Set<Photo> photos = categ.getPhotos();
						for (Photo photo : photos){
							path = firstPath+ photo.getPath()+photo.getPhName();
					%>			
	<% if (row == 4){
	%></tr><tr>
	<%row = 0;}else{
	row++;
	} %>
	<td class="tabsTableMiddle" ><div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=<%= photo.getPhotoId() %>"><img src="<%=path %>" alt="<%= photo.getPhName() %>" title="<%= photo.getPhName() %>" height="150px"/></a>
	</div>
		<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=<%= photo.getPhotoId() %>" class="link bold">Виж в цял размер</a>
		<%= photo.getPhName()  %>
	</div>
	
	<div class="lh17">Коментари <span class="bold"><%= photo.getComments().size() %></span></div>
	<br><br>
	<% if (userLogin != null){ %>
	<div style="color: blue;">
	<a href="comment.JSP?picPath=<%= photo.getPhotoId() %>" class="link bold" >Добави коментар</a>
	</div><% } %>
	<%if (user.equals(userLogin)){%>
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=<%= photo.getPhotoId() %>" class="link bold" >Изтрии</a>
	</div>
	<div style="color: green;">
	<a href="renamePicture.jsp?pic=<%= photo.getPhotoId() %>" class="link bold" >Преименувай</a>
	</div></td>
	
	<%}}}}else{
		Category categ = (Category)session.getAttribute("path");
		if (categ != null){
		Set<Photo> photos = categ.getPhotos();
		for (Photo photo : photos){
		path = firstPath+ photo.getPath()+photo.getPhName();
		pages = 1+photos.size()/8;
		%>
		<% if (row == 4){
	%></tr><tr>
	<%row = 0;}else{
	row++;
	} %>
			<td class="tabsTableMiddle" ><div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=<%= photo.getPhotoId() %>"><img src="<%=path %>" alt="<%= photo.getPhName() %>" title="<%= photo.getPhName() %>" height="150px"/></a>
	</div>
		<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=<%= photo.getPhotoId() %>" class="link bold">Виж в цял размер</a>
		<%= photo.getPhName()  %>
	</div>
	
	<div class="lh17">Коментари <span class="bold"><%= photo.getComments().size() %></span></div>
	<br><br>
	<% if (userLogin != null){ %>
	<div style="color: blue;">
	<a href="comment.JSP?picPath=<%= photo.getPhotoId() %>" class="link bold" >Добави коментар</a>
	</div><% } %>
	<%if (user.equals(userLogin)){%>
	
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=<%= photo.getPhotoId() %>" class="link bold" >Изтрии</a>
	</div>
	<div style="color: green;">
	<a href="renamePicture.jsp?pic=<%= photo.getPhotoId() %>" class="link bold" >Преименувай</a>
	</div></td>
	<% }}}}%>
</div>			
</td>
	<tr><td>
	<div class="fLeft left10">
				<a href="ShowUser.jsp?page=1" onclick="MainPage.jsp; return false; "><img src="img/btnLeft.gif" align="absmiddle" /></a>
				<a href="ShowUser.jsp?page=3" onclick="return toPage('next','men');"><img src="img/btnRight.gif" align="absmiddle" /></a>
				<span id="menPage">1</span> от <span id="menTotalPages"><%= pages %></span>
			</div></tr></td>
			

</div></td></tr>



</table>
</body>
</html>
