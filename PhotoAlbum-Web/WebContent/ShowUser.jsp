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
					<span class="mainTab1"><a href="ShowAllPictuers?param=<%= "allPictures" %>" title="allPictures">Виж всички снимки</a></span>
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
	
	
		<tr>
		<%String pages = (String)session.getAttribute("pag");
			
			if (session.getAttribute("nextPage") != null){
			Integer nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
			int nowPage = nextPage/6;
			String[] photoId = ((String[])session.getAttribute("photoId"));
			String[] photoComment=((String[])session.getAttribute("photoComment"));
			String[] pathAll=(String[])session.getAttribute("pathAll");
			String[] photoName=(String[])session.getAttribute("photoName");
			if (photoName.length>0){
			int count = 0;
			for (int k = 0; k < photoId.length;k++){
				
			if (photoName[k] != null){
		
			 if (count == 3){
	%></tr><tr>
	<%count = 0;}else{
	count++;
	} %>
	
	<div class="smallestProfile" >
	<td class="tabsTableMiddle" ><div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=<%= photoId[k] %>"><img src="<%=pathAll[k] %>" alt="<%= photoName[k] %>" title="<%= photoName[k] %>" 
		height="150px"/></a>
		</div>
		<div class="vipPic">&nbsp;</div>
		<div class="lh17"><%= pathAll[k] %>
		<a href="fullScreen.jsp?pic=<%= photoId[k] %>" class="link bold">Виж на цял екран</a>
		<%= photoName[k]  %>
	</div>
	<div class="lh17">Коментари<span class="bold"><%= photoComment[k].toString() %></span></div>
	<br><br>
	
	<% if (userLogin != null){ %>
	<div style="color: blue;">
	<a href="comment.jsp?picPath=<%=photoId[k] %>" class="link bold" >Добави коментар</a>
	</div><% } %>
	
	
	<%if (user.equals(userLogin)){%>
	
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=<%=photoId[k] %>" class="link bold" >Изтрии снимката</a>
	</div>
	<div style="color: green;">
	<a href="renamePicture.jsp?pic=<%= photoId[k] %>" class="link bold" >Преименувай</a>
	</div></td>
	

<%}} }%>							
	<tr>
		<td class="tabsBottomMid">
			<div class="fLeft left10">
				<a href="ShowPageServlet?page=prev" ><img src="img/btnLeft.gif" align="absmiddle" /></a>
				<a href="ShowPageServlet?page=next" ><img src="img/btnRight.gif" align="absmiddle" /></a>
				<span id="menPage"><%= nowPage %></span> от <span id="menTotalPages"><%= pages %></span>
			</div>
						
	<%}} %>							
	

			

</div></td></tr>



</table>
</body>
</html>
