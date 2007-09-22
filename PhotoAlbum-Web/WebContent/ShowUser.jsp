<%-- jsf:pagecode language="java" location="/src/pagecode/OldMainPage.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.Category"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="Themes/main.css" />
	<script type="text/javascript"></script>
	<title>ShowUser</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">

<script type="text/javascript">
function click(){
if(confirm("сигурен ли си, че искаш да изтриеш категориятa ")){
window.alert("изтрита");
window.location.href="DeleteCategoryServlet";

}
}
</script>
</head>
<body>
	<%	User userLogin = null;
		if (session.getAttribute("login")!=null){
			userLogin = (User)session.getAttribute("login");
		}
		User user = (User) session.getAttribute("user");
		Set<Category> category = user.getCategories();
		User[] allUsers = (User[]) session.getAttribute("allUser");
	%>
	<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
		<tr><td colspan="3" class="mainTop"></td></tr>
		<tr><td colspan="3" class="mainTopMenu">
			<table cellspacing="0" class="flex">
				<tr><td class="left pLeft10">Най-големият сайт за снимки в България! </td>
				<td class="right" align="right">
						<% if (userLogin == null) { %>
						<a href="register.jsp">Регистрация</a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% }else {  %>
						<a href="ShowUser.jsp"><%= "Добре дошъл, " + userLogin.getFirstName() + " " + userLogin.getLastName() %></a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						
						<a href="ExitServlet">Изход</a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% } %>
						<a href="SearchServlet">Търсене</a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<a href="Help.jsp">Помощ</a>
					</td></tr></table>
			
		<tr><td class="mainLeft vtop">
		 			
<!--left menu -->
			<table class="leftMenu" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="2"><div>Категории</div></td></tr>
				<tr><td class="fill"><img class="bullet" src="./img/bullet.png" /></td>
				<td class="fill"><a href="ShowPageServlet?category=null">Виж всички снимки</a></td>
			</tr>
				<%	if (category != null) {
						for (Category categ: category) {
				%>
				<tr>
					<td class="fill"><img class="bullet" src="./img/bullet.png" /></td>
					<td class="fill"><a href="ShowPageServlet?category=<%= categ.getCatName() %>"><%= categ.getCatName() %></a></td>
			
				</tr>
				<%		}
					}
				%>
				<tr><td class="bottom" colspan="2"></td></tr>
			</table>
<!--left menu end -->

		<%	if (session.getAttribute("categoryToView")!=null){
				String pages = (String) session.getAttribute("pag");
				int currentPage = 1;
				if (session.getAttribute("nowPage")!=null){
					currentPage = Integer.parseInt(session.getAttribute("nowPage").toString());
				}
				pages = "" + 1;
				if (session.getAttribute("allPages") != null) {
					pages = (String) session.getAttribute("allPages");
				}
				Integer nextPage = Integer.valueOf((Integer) session.getAttribute("nextPage"));
				int photoAtPage = Integer.valueOf((Integer) session.getAttribute("photoAtPage"));
				int photoAtRow = Integer.valueOf((Integer) session.getAttribute("photoAtRow"));
				int nowPage = nextPage / photoAtPage;
				String[] photoId = ((String[])session.getAttribute("photoId"));
				String[] photoComment=((String[])session.getAttribute("photoComment"));
				String[] pathAll=(String[])session.getAttribute("pathAll");
				String[] photoName=(String[])session.getAttribute("photoName");
				int count = 0;
		%>
				<div class="kare">
					<table cellpadding="0" cellspacing="0" class="top10">
						<tr><td class="headerBorder headerMin" colspan="2">
								<div style="width: 150px;">Mясто за реклама</div>
							</td>
						</tr>
						<tr><td class="fill"><a href="http://www.amam.bg/" target="_blank" title="кликни &amp; хапни"><img src="img/kare_amam.jpg" alt="кликни &amp; хапни" align="left" /></a>
							</td>
							<td class="fill">
								<div><a href="http://www.amam.bg/" class="bold purple2" target="_blank" title="кликни &amp; хапни">Amam.bg</a></div>
								<div class="size10"><a href="http://www.amam.bg/?ad=004;00;03" class="black" target="_blank" title="кликни &amp; хапни">Кликни &amp; Хапни</a></div>
							</td>
						</tr>
						<tr><td class="fill"><a href="http://www.amam.bg/" target="_blank" title="Nani.bg - внесете уют"><img src="img/nani_1_40x40.jpg" alt="Nani.bg" align="left" /></a>
							</td>
							<td class="fill">
								<div><a href="http://www.nani.bg" class="bold purple2" target="_blank" title="Nani.bg - внесете уют">Nani.bg</a></div>
								<div class="size10"><a href="http://www.nani.bg" class="black" target="_blank" title="Nani.bg - внесете уют">Внесете Уют</a></div>
							</td>
						</tr>
						
						<tr><td class="bottom" colspan="2"></td></tr>
					</table>
				</div>
			</td>				
<!-- search banner -->
			<td colspan="2" class="mainCenterNone vtop">
				<table cellpadding="0" cellspacing="0" class="flex" align="center" >
					<tr><td class="vtop">
						<table cellpadding="0" cellspacing="0" class="mainSearch top10" align="center">
							<tr><td class="headerBorder headerMid center">
									<div style="width: 400px;">Търси в най-големият сайт за снимки в България</div>
								</td>
							</tr>
							<tr><td class="mainSearchTd">
								<div class="fLeft pLeft10 pTop10">
									<form action="SearchServlet" method="post">
										<table cellpadding="0" cellspacing="0" class="searchTable" align="center">
											<tr align="center"><td width="100px">Име на снимка: </td>
												<td width="160px"><input type="text" class="textInput" name="searchName" id="searchName" /></td>
												<td colspan="2" width="130px">
													<input type="submit" class="button" style="width:90px;" name="btnSearch" value="Търси" />
												</td>
												<%	String search = (String) request.getAttribute("search");
													if (search != null) {
												%>	<tr><td><%= search %></td></tr>
												<%	} %>
											</tr>
										</table>
									</form>
								</div>
								</td>
							</tr>
							<tr><td class="mainSearchTd lh10">&nbsp;</td></tr>
							<tr><td class="bottomMid">&nbsp;</td></tr>
						</table>
						<table class="tabsMiddle top10" cellpadding="0" cellspacing="0" align="center" >
						<%	for (int k = 0; k < photoId.length; k++) {
								if (photoName[k] != null){
									if (count == photoAtRow) {
										count = 1;
						%>
							<tr>
								<%	} else {
										count++;
									}
								%>
								<td class="tabsTableMiddle" align="center" width="200px">
									<div class="smallestProfile" align="center">
										<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>">
											<img src="<%= pathAll[k] %>"  height="127px" width="170px" alt="" title="<%= photoName[k] %>" />
										</a>
										<%	String[] viewName = photoName[k].split("[.]");
											if (viewName[0].length() > 20) {
												viewName[0] = viewName[0].substring(0,19);
											}
										%>
										<div class="lh17">
											<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>" class="link bold"><%= viewName[0] %></a>
											<div class="lh17">Коментари <span class="bold"><%= photoComment[k] %></span></div>
										</div>
					<%		}
						 }
					%>							
						<tr>
							<td class="tabsBottomMid" width="600">
								<div class="fLeft left10">
								<%	int prevPage = currentPage - 1;
									nextPage = currentPage + 1;
								%>
									<a href="ShowPageServlet?page=<%= prevPage %>" ><img src="img/btnLeft.gif" align="absmiddle" /></a>
									<a href="ShowPageServlet?page=<%= nextPage %>" ><img src="img/btnRight.gif" align="absmiddle" /></a>
									<span id="menPage"><%= currentPage %></span> от <span><%= pages %></span>
								</div>
								<div class="fRight right10">
									<div style="line-height:17px;"></div>
								</div>
							</td>
						</tr>
					</table></table>
		<%} %>			
				</td>
			
					
	<tr><td colspan="3" style="line-height: 0px; height: 10px;">&nbsp;</td>
	</tr>
	<tr><td colspan="3" class="mainBottom">
		<div>
			<div class="fLeft vtop left10 top10">
				<a class="link" href="http://academy.devbg.org/">За контакти</a><span class="separator">|</span>
				<a class="link" href="Advertisement.jsp">За реклама</a><span class="separator">|</span>
				<a class="link" href="Dot.jsp">Права и задължения</a><span class="separator">|</span>
				<a class="link" href="Help.jsp">Помощ</a><span class="separator">|</span>
				<div class="left">
					<div style="padding-top: 10px;padding-bottom: 10px;">
						<span class="separator" />
						<span class="separator" />
					</div>
				</div>
			</div>
			<div class="fRight vtop right10 top10">
				Copyright © 2007-2007 Менте Софтуер<br>
				<div class="right">Web Design: НАРС</div>
			</div>
		</div>
		</td>
	</tr>
</table>
</body>
</html>