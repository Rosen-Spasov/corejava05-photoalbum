<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.Photo"%>
<%@page import="photoalbum.entities.Comment"%>
<html>
<link rel="stylesheet" type="text/css" href="Themes/showUser.css" />


<head>
<title>ShowUser</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">

<SCRIPT language="JavaScript1.2">
function popuponclick()
{
 my_window = window.open("",
    "mywindow","status=1,width=350,height=150");
  my_window.document.write('<div style="color: red;">сигурен ли сте че искате да изтриете категорията<B> <%= session.getAttribute("currentCategory")%></b></div>');
my_window.document.write('<input type="button" value="Да" onclick="window.close()">');   
my_window.document.write('<input type="button" value="Не" onclick="window.close()">'); 
} 
function closepopup()
{
 if(false == my_window.closed) 
 {

	  my_window.close (); 
 }
 else
 {
    alert('Window already closed!');
 }
}
</SCRIPT>
</head>
<body>

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
<%
User userLogin = (User) session.getAttribute("login");
%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
%>
<table class="main_table" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td></td>
		<td>
		<div style="color: red;">За съжаление няма намерен потребител с
		това име</div>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><a href="MainPage.jsp">връщане в начална страница</a> <%
 } else {
 %>
		
	<tr>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td style="" align="center">
		<div style="font-size: 20px; color: maroon; background-color: aqua"
			align="center"><%="Това е профила на " + user.getFirstName() + " "+ user.getLastName()%></div>
		</td>
	</tr>
	<tr>
		<td colspan="2"
			style="width: 950px; height: 21px; padding:0; margin:0; background-color: white;"
			class="">
		<table class="mainTabsTable" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td class="mainMenu">
				<%
						Set<Category> category = user.getCategories();
						String[] type = { "mainTab searchTab", "mainTab profileTab",
						"mainTab messagesTab", "mainTab invTab",
						"mainTab startTab selectedStart", "mainTab videoTab",
						"mainTab photoTab", "mainTab groupsTab" };
				%>

				<div class="<%= type[0] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a href="Search.jsp"
					title="Търсене">Търсене</a></span> <img src="img/tr.gif" class="tr"
					alt="tr" /></div>

				<%
				if (user.equals(userLogin)) {
				%>
				<div class="<%= type[1] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="addCategory.jsp" title="Добави категория">Добави
				категория</a></span> <img src="img/tr.gif" class="tr" alt="tr" /></div>
				<%
						if (session.getAttribute("currentCategory") != null) {
						String cat = (String) session.getAttribute("currentCategory");
						if (!cat.equalsIgnoreCase("allPictures")) {
				%>
				<div class="<%= type[2] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a href="renameCategory.jsp" title="Категория">Преименувай категория</a>
					</span> <img src="img/tr.gif" class="tr" alt="tr" /></div>
				<div class="<%= type[3] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="ShowAllPictuers" title="Добави снимка">Добави снимка</a></span> <img
					src="img/tr.gif" class="tr" alt="tr" /></div>
				<div class="<%= type[5] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="javascript: popuponclick()" title="Категория">Изтрии категория</a></span> <img src="img/tr.gif" class="tr" alt="tr" /></div>
				<%
							}
							}
				%> <%
 }
 %> <%
 if (userLogin == null) {
 %>

				<div class="sideMenuLinks"><a class="grey" href="register.jsp"
					title="Регистрация в Sibir.bg">Регистрация</a></div>
				<div class="sideMenuLinks"><a class="grey" href="MainPage.jsp"
					title="Вход в photo album">Вход</a><span class="separator grey">|</span></div>
				<%
				} else {
				%>
				<div class="sideMenuLinks"><a class="grey" href="ExitServlet"
					title="Регистрация в Sibir.bg">Излез</a></div>
				<%
				}
				%>
				<div class="sideMenuLinks"><a class="grey" href="MainPage.jsp"
					title="Вход в photo album">Начална страница</a><span
					class="separator grey">|</span></div>
				</td>
			</tr>

			<tr>
				<td class="mainMenu">
				<%
				int t = 1;
				%>
				<div class="<%= type[0] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="ShowAllPictuers?param=<%= "allPictures" %>"
					title="allPictures">Виж всички снимки</a></span> <img src="img/tr.gif"
					class="tr" alt="tr" /></div>
				<%
				for (Category categ : category) {
				%>
				<div class="<%= type[t] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="ShowAllPictuers?param=<%= categ %>" title="<%= categ %>"><%=categ%></a></span>
				<img src="img/tr.gif" class="tr" alt="tr" /></div>
				<%
						t++;
						}
				%>
				</td>
			</tr>
			<% if (session.getAttribute("childCategories")!=null){
				t = 0;
				String[] childCategories =(String[])session.getAttribute("childCategories"); %>
				<tr>
				<% for (Category childCateg : category) {
				%>
				<div class="<%= type[t] %>"><img src="img/tl.gif" class="tl"
					alt="tl" /> <span class="mainTab1"><a
					href="ShowAllPictuers?param=<%= childCateg %>" title="<%= childCateg %>"><%=childCateg%></a></span>
				<img src="img/tr.gif" class="tr" alt="tr" /></div>
				<%t++;}%>
				</tr>
				<%	}%>

		</table>
		</td>
	</tr>
	<%
	}
	%>
	<table class="tabsMiddle top10" cellpadding="2" cellspacing="0"
		align="center">
		<tr>
			<td class="tabs">
			<div class="rightTabStub">&nbsp;</div>


			<div class="leftTabStubMiddle">&nbsp;</div>
			</td>
		</tr>
		<tr>
			<%
				String[] owner = null;
				if (session.getAttribute("owner") != null) {
					owner = (String[]) session.getAttribute("owner");
				}
			%>
			<%
				String pages = (String) session.getAttribute("pages");
				if (session.getAttribute("photoAtPage") != null) {
					if (session.getAttribute("nextPage") != null) {
						if (session.getAttribute("photoAtRow") != null) {
					Integer nextPage = Integer.valueOf((Integer) session.getAttribute("nextPage"));
					int photoAtPage = Integer.valueOf((Integer) session.getAttribute("photoAtPage"));
					int photoAtRow = Integer.valueOf((Integer) session.getAttribute("photoAtRow"));
					int nowPage = nextPage / photoAtPage;
					String[] photoId = ((String[]) session.getAttribute("photoId"));
					String[] photoComment = ((String[]) session.getAttribute("photoComment"));
					String[] pathAll = (String[]) session.getAttribute("pathAll");
					String[] photoName = (String[]) session.getAttribute("photoName");
					if (photoName.length > 0) {
						int count = 0;
						for (int k = 0; k < photoId.length; k++) {
							if (photoName[k] != null) {
								if (count == photoAtRow) {
			%>
		</tr>
		<tr>
			<%
						count = 1;
						} else {
							count++;
						}
			%>

			<div class="smallestProfile">
			<td class="tabsTableMiddle">
			<div class="smallProfilePicOnline"><a href="fullScreen.jsp?pic=<%= photoId[k] %>"><img src="<%=pathAll[k] %>" alt="<%= photoName[k] %>"
				title="<%= photoName[k] %>" height="150px" /></a></div>
			<%
			if (owner != null) {
			%> Собственик на тази снимка<%=owner[k]%><br>
			<%}%><%= pathAll[k] %>
			<div class="lh17"><a href="fullScreen.jsp?pic=<%= photoId[k] %>" class="link bold">Виж на цял екран</a> <br>
			<%=photoName[k]%></div>
			<div class="lh17">Коментари<span class="bold"><%=photoComment[k].toString()%></span></div>
			<br>
			<br>

			<%
			if (userLogin != null) {
			%>
			<div style="color: blue;"><a href="comment.jsp?pic=<%=photoId[k] %>" class="link bold">Добави коментар</a></div>
			<% } %> 
			<% if (user.equals(userLogin)) { %>

			<div style="color: red;">
			<div><a href="deletePicture.jsp?pic=<%=photoId[k] %>"
				class="bold purple2" title="Изтрии снимката">Изтрии снимката</a></div>

			</div>
			<div style="color: green;"><a
				href="renamePicture.jsp?pic=<%= photoId[k] %>" class="link bold">Преименувай</a>
			</div>
			</td>


			<%
							}
							}
						}
					}
			%>
		
		<tr>
			<td class="tabsBottomMid">
			<div class="fLeft left10"><a href="ShowPageServlet?page=prev"><img
				src="img/btnLeft.gif" align="absmiddle" /></a> <a
				href="ShowPageServlet?page=next"><img src="img/btnRight.gif"
				align="absmiddle" /></a> <span id="menPage"><%=nowPage%></span> от <span
				id="menTotalPages"><%=pages%></span></div>

			<%
					}
					}
				}
			%>


			</div>
			</td>
		</tr>

	</table>
</body>
</html>
