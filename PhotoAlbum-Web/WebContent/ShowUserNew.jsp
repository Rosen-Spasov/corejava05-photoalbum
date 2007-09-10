<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="photoalbum.entities.Category"%>
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
<% String id = (String)request.getParameter("param1");%>
<% User userLogin = (User)session.getAttribute("login"); %>
<% User user = (User)session.getAttribute("user"); 
	if (user == null){
	%>
	<table class="main_table" align="center" cellpadding="0" cellspacing="0" >
	<tr><td></td><td><div style="color: red;">За съжаление няма намерен потребител с това име</div></td></tr>
	<tr><td></td><td><a href="MainPage.jsp">връщане в начална страница</a>
	<% }else{%>
	<tr><td></td><td>${user.firstName}</td></tr>
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
				<%if (userLogin.equals(user)){
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
			<% } %>
				</td></tr>
				<% 
				Set<Category> category = user.getCategories();
				String[] type = {"mainTab searchTab","mainTab profileTab","mainTab messagesTab","mainTab invTab",
				"mainTab startTab selectedStart","mainTab videoTab","mainTab photoTab","mainTab groupsTab"};
				  int catSize = category.size();
				  out.println(catSize);
				 for (int cat=0;cat<=catSize;){ 
				
				 %>
				<tr>
		<td class="mainMenu">	
		<%int t=0;
		 for(Category categ : category){%>
				<div class="<%= type[t] %>">
					<img src="img/tl.gif" class="tl" alt="tl" />
					<span class="mainTab1"><a href="ShowAllPictuers?param=<%= categ %>" title="<%= categ %>"><%= categ %></a></span>
					<img src="img/tr.gif" class="tr" alt="tr" />
				</div>
				<% }%>
		</td>
	</tr>
</table>				</td>
			</tr>
	<% }%>
	<table class="tabsMiddle top10" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td class="tabs">
			<div class="rightTabStub">&nbsp;</div>
		
			
			<div class="leftTabStubMiddle">&nbsp;</div>
		</td>
	</tr>
	<tr>
		<td class="tabsTableMiddle">
			<div class="loadingPictures" id="PicturesLoading" style="display:none;">&nbsp;</div>
			<div id="women">
									
<div class="smallestProfile" >
	<div class="smallProfilePicOnline" >
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\alonso.jpg"><img src="C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\alonso.jpg" alt="alonso" title="alonso" height="150px"/></a>
	</div>
			<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\alonso.jpg" class="link bold">Виж в цял размер</a>
		Алонсо
	</div>
	<div class="lh17">София</div>
	<div class="lh17">Koментари <span class="bold">2851</span></div>
	<%if (userLogin.equals(user)){%>
	<br><br>
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\alonso.jpg" class="link bold" >Изтрии</a>
	</div>
	<div style="color: green;">
	<a href="renamePicture.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\alonso.jpg" class="link bold" >Преименувай</a>
	</div>
	<%} %>
</div>			
</td><td>

<div class="smallestProfile">
	<div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\briatore.jpg"><img src="C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\briatore.jpg" alt="alonso" title="alonso" height="150px"/></a>
	</div>
			<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\briatore.jpg" class="link bold">Виж в цял размер</a>
		Алонсо
	</div>
	<div class="lh17">София</div>
	<div class="lh17">Koментари <span class="bold">2851</span></div>
	<%if (userLogin.equals(user)){%>
	<br><br>
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=alonso.jpg" class="link bold" >Изтрии</a>
	</div>
	<%} %>
</div>			
</td><td>
<div class="smallestProfile">
	<div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\ferrari.jpg"><img src="C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\ferrari.jpg" alt="alonso" title="alonso" height="150px"/></a>
	</div>
			<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\ferrari.jpg" class="link bold">Виж в цял размер</a>
		Алонсо
	</div>
	<div class="lh17">София</div>
	<div class="lh17">Koментари <span class="bold">2851</span></div>
	<%if (userLogin.equals(user)){%>
	<br><br>
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=alonso.jpg" class="link bold" >Изтрии</a>
	</div>
	<%} %>
</div></td>
<td>
<div class="smallestProfile">
	<div class="smallProfilePicOnline">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\renault.gif"><img src="C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\renault.gif" alt="alonso" title="alonso" height="150px"/></a>
	</div>
			<div class="vipPic">&nbsp;</div>
		<div class="lh17">
		<a href="fullScreen.jsp?pic=C:\ZaharyAnastasov\Project\PhotoAlbum\marin85\formula1\renault.gif" class="link bold">Виж в цял размер</a>
		Алонсо
	</div>
	<div class="lh17">София</div>
	<div class="lh17">Koментари <span class="bold">2851</span></div>
	<%if (userLogin.equals(user)){%>
	<br><br>
	<div style="color: red;">
	<a href="DeletePictureServlet?pic=alonso.jpg" class="link bold" >Изтрии</a>
	</div>
	<%} %>
</div></td>
	<tr><td>
	<div class="fLeft left10">
				<a href="ShowUser.jsp?page=1" onclick="MainPage.jsp; return false; "><img src="img/btnLeft.gif" align="absmiddle" /></a>
				<a href="ShowUser.jsp?page=3" onclick="return toPage('next','men');"><img src="img/btnRight.gif" align="absmiddle" /></a>
				<span id="menPage">1</span> от <span id="menTotalPages">666</span>
			</div></tr></td>
			

</div></td></tr>
</table>
</body>
</html>
