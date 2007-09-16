<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="photoalbum.PhotoAlbumManipulator"%>
<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.Photo"%>
<html>
<head>
	<title>Фото албум - Намери снимките, които търсиш!</title>
	<link rel="stylesheet" type="text/css" href="Themes/main.css" />
	<script type="text/javascript"></script>
</head>
<body onload="">
	<% User userLogin = (User)session.getAttribute("login"); %>
	<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
		<tr><td colspan="3" class="mainTop"></td></tr>
		<tr><td colspan="3" class="mainTopMenu">
			<table cellspacing="0" class="flex">
				<tr><td class="left pLeft10">Най-големият сайт за снимки в България! </td><td class="right">
						<% if (userLogin == null) { %>
						<a href="register.jsp">Регистрация</a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% }else {  %>
						<a href="ShowUser.jsp"><%= "Добре дошъл " + userLogin.getFirstName() + " " + userLogin.getLastName() %></a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% session.setAttribute("user",userLogin); %>
						<a href="ExitServlet">Изход</a><span class="separator"><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% } %>
						<a href="SearchServlet">Търсене</a><span class="separator"><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<a href="Help.jsp">Помощ</a>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr><td class="mainLeft vtop">
					<!--loginform -->
			<form action="LoginServlet" method="post">
				<table cellpadding="0" cellspacing="0">
					<tr><td class="leftLoginTop">&nbsp;</td></tr>
					<tr><td><table cellpadding="0" cellspacing="0" class="leftLoginForm">
							<% if (userLogin == null) { %>
								<tr><td class="left pTop10 pLeft10"><label for="userName">Име: </label></td>
									<td class="left pTop10 pRight10"><input type="text" class="textInput" name="username" id="username" /></td>
								</tr>
								<tr><td class="left pLeft10"><label for="pass">Парола: </label></td>
									<td class="left pRight10"><input type="password" class="textInput" name="pass" id="pass" /></td>
								</tr><td class="right">&nbsp;</td>
								<% if (session.getAttribute("errors")!=null){
									String[] errors = (String[])session.getAttribute("errors");
									for (String err: errors){
										if (err!=null){%>
								<tr><td colspan="2"><%= err %></td></tr>
								<%} } }%>
									<tr><td></td>
									<td class="left">
									<input type="submit" class="button" style="width: 90px;" name="login" id="login" value="Вход" />
									</td>
								</tr>
							<% } %>
							</table>
						</td>
					</tr>
					<tr><td class="leftLoginBottom">&nbsp;</td></tr>
				</table>
			</form>
<!--left menu -->
			<table class="leftMenu top10" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="2"><div>Потребители</div></td></tr>
				<%	User[] allUsers = (User[]) session.getAttribute("allUser");
					if (allUsers != null) {
						for (User user: allUsers) {
				%>
				<tr>
					<td class="fill" width="10px"><img class="bullet" src="./img/bullet.png" /></td>
					<td class="fill"><a href="SearchServlet?searchName=<%= user.getUsername() %>"><%= user.getUsername() %></a></td>
				</tr>
				<%		}
					}
				%>
				<tr><td class="bottom" colspan="2"></td></tr>
			</table>
<!--left menu end -->

		<%	String ref = (String)session.getAttribute("ref");
			if (ref == null){
			response.sendRedirect("PageServlet");
		
			} else {
				session.setAttribute("ref", null);
				String pages = (String)session.getAttribute("pag");
				if (session.getAttribute("nextPage")!=null){
				Integer nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
				
				int nowPage = nextPage/6;
				
				String[] photoId = ((String[])session.getAttribute("photoId"));
				String[] photoComment=((String[])session.getAttribute("photoComment"));
				String[] pathAll=(String[])session.getAttribute("pathAll");
				String[] photoName=(String[])session.getAttribute("photoName");
				ref = null;
				int count = 0;
		%>

			<div class="kare">
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td class="headerBorder headerMin">
							<div style="width: 150px;">Mясто за реклама</div>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td>
							<a href="http://www.atol.bg" target="_blank" title="Atol.bg - Съученици и Приятели"><img src="http://img.elmaz.com/style/img/kare/kare_atol.gif" alt="Atol.bg - Съученици и Приятели" align="left" /></a>
						</td>
						<td class="pLeft10">
							<div><a href="http://www.atol.bg" class="bold purple2" target="_blank" title="Atol.bg - Съученици и Приятели">Atol.bg</a></div>
							<div class="size10"><a href="http://www.atol.bg" class="black" target="_blank" title="Atol.bg - Съученици и Приятели">Съученици и Приятели</a></div>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td>
							<a href="http://www.amam.bg/" target="_blank" title="кликни &amp; хапни"><img src="img/kare_amam.jpg" alt="кликни &amp; хапни" align="left" /></a>
						</td>
						<td class="pLeft10">
							<div><a href="http://www.amam.bg/" class="bold purple2" target="_blank" title="кликни &amp; хапни">Amam.bg</a></div>
							<div class="size10"><a href="http://www.amam.bg/?ad=004;00;03" class="black" target="_blank" title="кликни &amp; хапни">кликни &amp; хапни</a></div>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td>
							<a href="http://www.amam.bg/" target="_blank" title="Nani.bg - внесете уют"><img src="img/nani_1_40x40.jpg" alt="Nani.bg" align="left" /></a>
						</td>
						<td class="pLeft10">
							<div><a href="http://www.nani.bg" class="bold purple2" target="_blank" title="Nani.bg - внесете уют">Nani.bg</a></div>
							<div class="size10"><a href="http://www.nani.bg" class="black" target="_blank" title="Nani.bg - внесете уют">внесете уют</a></div>
						</td>
					</tr>
				</table>
			</div>
			<hr />
			<div>
				<table cellpadding="0" cellspacing="0" class="top5">
					<tr>
						
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top5">
					<tr>
					</tr>
				</table>
			</div>					
<!--left banner -->
			</td>
			<td colspan="2" class="mainCenterNone vtop">
				<table cellpadding="0" cellspacing="0" class="flex">
					<tr><td class="vtop">
						<table cellpadding="0" cellspacing="0" class="mainSearch top10">
							<tr><td class="headerBorder headerMid center">
									<div style="width: 400px;">Търси в най-големият сайт за снимки в България</div>
								</td>
							</tr>
							<tr><td class="mainSearchTd">
								<div class="fLeft pLeft10 pTop10">
									<form action="SearchServlet" method="post">
										<table cellpadding="0" cellspacing="0" class="searchTable">
											<tr><td colspan="2">търсенето става по име и/или категория</td></tr>
											<tr><td>Име: </td>
												<td><input type="text" class="textInput" name="searchName" id="searchName" /></td>
											</tr>
											<tr><td>Категория: </td>
												<td><input type="text" class="textInput" name="category" id="category" /></td>
											</tr>
											<tr><td colspan="2">
												<input type="submit" class="button" style="width:90px;" name="btnSearch" value="Търси" />
												</td>
											</tr>
										<%	String search = (String) request.getAttribute("search");
											if (search != null) {
										%>	<tr><td><%= search %></td></tr>
										<%	} %>
										</table>
									</form>
								</div>
								<div class="fRight top10 right10">
									<div class="stats">
										<div class="bold">Регистрации: </div>
										<div>Общо: <span class="bold"><%= allUsers.length %></span></div>
								
									</div>
									<div class="center" style="line-height:17px;">
										<a href="register.jsp" class="link bold">Регистрирай се</a>
									</div>
								</div>
								</td>
							</tr>
							<tr>
								<td class="mainSearchTd lh10">&nbsp;</td>
							</tr>
							<tr>
								<td class="bottomMid">&nbsp;</td>
							</tr>
						</table>
					<tr><td class="tabs">
						<div class="rightTabStub">&nbsp;</div>
						<div class="tab tabSelected" ></div>
						<div class="leftTabStubMiddle">&nbsp;</div>
						</td>
					</tr>
					<table class="tabsMiddle top10" cellpadding="0" cellspacing="0">
						<tr>
					<%	for (int k = 0; k < photoId.length;k++) {
							if (photoName[k] != null){
								if (count == 3) {
									count = 1;
					%>
						</tr>
						<tr>
							<%	} else {
									count++;
								}
							%>
							<td class="tabsTableMiddle">
								<div class="loadingPictures" id="PicturesLoading" style="display:none;">&nbsp;</div>
								<div id="women">
									<div class="smallestProfile">
										<div class="smallProfilePicOnline">
											<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>"><img src="<%= pathAll[k] %>" height="100" alt="" title="<%= photoName[k] %>" /></a>
										</div>
										<div class="vipPic">&nbsp;</div>
										<div class="lh17">
											<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>" class="link bold">Виж на цял екран</a>
											<%= photoName[k]  %>
										</div>
										<div class="lh17">Коментари <span class="bold"><%= photoComment[k] %></span></div>
	
									</div>
					<%		}
						} 
					%>							
						<tr>
							<td class="tabsBottomMid">
								<div class="fLeft left10">
									<a href="PageServlet?page=prev" ><img src="img/btnLeft.gif" align="absmiddle" /></a>
									<a href="PageServlet?page=next" ><img src="img/btnRight.gif" align="absmiddle" /></a>
									<span id="menPage"><%= nowPage %></span> от <span id="menTotalPages"><%= pages %></span>
								</div>
								<div class="fRight right10">
									<div style="line-height:17px;">
										
									</div>
								</div>
							</td>
						</tr>
					</table>
			<%} }%>			
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="3" style="line-height: 0px; height: 10px;">&nbsp;</td></tr>
	<tr><td colspan="3" class="mainBottom"></td></tr>
</table>
</body>
</html>