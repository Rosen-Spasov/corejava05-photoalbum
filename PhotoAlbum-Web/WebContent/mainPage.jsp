<%-- jsf:pagecode language="java" location="/src/pagecode/OldMainPage.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="photoalbum.entities.User"%>

<html>

<head>
	<title>Фото албум - Намери снимките, които търсиш!</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
	<script type="text/javascript"></script>
</head>

<body onload="">
<%	User loggedUser = (User) session.getAttribute("login");	%>
<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
	<tr><td class="mainTop" colspan="3">
		<div class="mainTopLogo">
			<img src="./img/headBG.png" class="noBorder" USEMAP="#link" />
			<map name="link"><area shape="rect" coords="15,10,225,60" href="mainPage.jsp">
			</map>
			<div class="mainTopBanner">
				<div><a href='' title="" class="headerTexLinks">Дали това не е най-големият сайт за снимки в България?!</a>
				</div>
			</div>
		</div>
		</td>
	</tr>
	<tr><td class="vseparator">&nbsp;</td></tr>		
	<tr><td colspan="3" class="mainTopMenu">
		<table cellpadding="0" cellspacing="0" class="flex">
			<tr><td class="left pLeft10">Най-големият сайт за снимки в България!</td>
				<td class="right pRight10">
					<c:choose>
						<c:when test="${empty loggedUser}">
							<a href="register.jsp">Регистрация</a><span class="separator"><img src="./img/separator.png" align="absmiddle" /></span>
						</c:when>
						<c:otherwise>
							<c:out value='Добре дошъл, ${loggedUser.firstName} ${loggedUser.lastName}' /><span class="separator"><img src="img/separator.png" align="middle" /></span>
							<% session.setAttribute("user", loggedUser); %>
							<a href="ExitServlet">Изход</a><span class="separator"><img src="./img/separator.png" align="middle" /></span>
						</c:otherwise>
					</c:choose>
					<a href="SearchServlet">Търсене</a><span class="separator"><img src="./img/separator.png" align="middle" /></span>
					<a href="Help.jsp">Помощ</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr><td class="mainLeft vtop">
		 <% if (loggedUser == null) { %>
			<form action="LoginServlet" method="post">
				<table cellpadding="0" cellspacing="0">
					<tr><td class="leftLoginTop top10">&nbsp;</td></tr>
					<tr><td><table cellpadding="0" cellspacing="0" class="leftLoginForm">
								<tr><td class="left pTop10 pLeft10"><label for="userName">Име: </label></td>
									<td class="left pTop10 pRight10"><input type="text" class="textInput" name="username" id="username" /></td>
								</tr>
								<tr><td class="left pLeft10"><label for="pass">Парола: </label></td>
									<td class="left pRight10"><input type="password" class="textInput" name="pass" id="pass" /></td>
								</tr>
								<%	if (session.getAttribute("errors") != null) {
										String[] errors = (String[]) session.getAttribute("errors");
										for (String err : errors){
											if (err != null) {
								%>
								<tr><td colspan="2"><%= err %></td></tr>
								<%			}
										}
									}
								%>
								<tr><td></td>
									<td class="left">
										<input type="submit" class="button" style="width: 90px;" name="login" id="login" value="Вход" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td class="leftLoginBottom">&nbsp;</td></tr>
					<tr><td style="font-size: 8px;">&nbsp;</td></tr>
				</table>
			</form>
		<% } %>
			<table class="leftMenu" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="2"><div>Потребители</div></td></tr>
				<%	User[] allUsers = (User[]) session.getAttribute("allUser");
					if (allUsers != null) {
						for (User user: allUsers) {
				%>
				<tr><td class="fill"><img class="bullet" src="./img/bullet.png" /></td>
					<td class="fill"><a href="SearchServlet?searchName=<%= user.getUsername() %>"><%= user.getUsername() %></a></td>
				</tr>
				<%		}
					}
				%>
				<tr><td class="bottom" colspan="2"></td></tr>
			</table>
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
	</tr>
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