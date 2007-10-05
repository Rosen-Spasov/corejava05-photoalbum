<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Фото албум - Намери снимките, които търсиш!</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
	<script type="text/javascript"></script>
</head>

<body>
<c:if test="${empty sessionScope.initialized || param.refresh == true}">
	<c:redirect url="/initSession" />
</c:if>
<c:set var="allUsers" value="${sessionScope.allUsers}" />
<c:set var="loggedUser" value="${sessionScope.loggedUser}" />
<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
	<tr><td class="mainTop" colspan="3">
		<div class="mainTopLogo">
			<img src="./images/headBG.png" class="noBorder" USEMAP="#link" />
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
							<a href="register.jsp">Регистрация</a>
							<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span>
						</c:when>
						<c:otherwise>
							<font style="font-size: 12px;">
								<c:out value='Добре дошъл, ${loggedUser.firstName} ${loggedUser.lastName}' />
								<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span>
							</font>
							<a href="./login?action=logout">Изход</a>
							<span class="separator"><img src="./images/separator.png" align="middle" /></span>
						</c:otherwise>
					</c:choose>
					<a href="SearchServlet">Търсене</a>
					<span class="separator"><img src="./images/separator.png" align="middle" /></span>
					<a href="mainPage.jsp?refresh=true">Начало</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr><td class="mainLeft vtop">
			<c:choose>
				<c:when test="${empty loggedUser}">
					<form method="POST" action="./login?action=login">
						<table cellpadding="0" cellspacing="0">
							<tr><td class="leftLoginTop top10">&nbsp;</td></tr>
							<tr><td><table cellpadding="0" cellspacing="0" class="leftLoginForm">
										<tr><td class="left pTop10 pLeft10"><label for="user">Име: </label></td>
											<td class="left pTop10 pRight10"><input type="text" class="textInput" name="username" id="user" /></td>
										</tr>
										<tr><td class="left pLeft10"><label for="pass">Парола: </label></td>
											<td class="left pRight10"><input type="password" class="textInput" name="password" id="pass" /></td>
										</tr>
										<c:if test="${!empty sessionScope.errors}">
											<c:forEach var="error" items="${sessionScope.errors}">
												<tr><td colspan="2">
														<c:out value="${error}" />
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<tr><td></td>
											<td class="left">
												<input type="submit" class="button" style="width: 90px;" name="login" id="login" value="Вход" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td class="leftLoginBottom">&nbsp;</td></tr>
						</table>
					</form>
					<div class="lh10">&nbsp;</div>
				</c:when>
			</c:choose>
			<table cellpadding="0" cellspacing="0" class="leftMenu">
				<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">
						<div>Потребители</div>
					</td>
				</tr>
				<c:if test="${!empty sessionScope.allUsers}">
					<c:forEach var="user" items="${sessionScope.allUsers}">
						<tr><td class="leftItem pTop10">
								<img src="./images/bullet.png" class="bullet" alt="bullet" />
							</td>
							<td class="rightItem pTop10">
								<a href="./user?action=LOAD&userId=${user.userId}">${user.username}</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr><td class="leftItem lh10">&nbsp;</td>
					<td class="rightItem lh10">&nbsp;</td>
				</tr>
				<tr><td colspan="2" class="bottomMin">&nbsp;</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" class="top10" width="170px">
				<tr><td class="headerBorder headerMin" colspan="2">
						<div style="width: 150px;">Mясто за реклама</div>
					</td>
				</tr>
				<tr><td class="pTop10"><a href="http://www.amam.bg/" title="кликни &amp; хапни"><img src="./images/kare_amam.jpg" alt="кликни &amp; хапни" align="left" /></a>
					</td>
					<td><div><a href="http://www.amam.bg/" class="link bold" title="кликни &amp; хапни">Amam.bg</a></div>
						<div class="size10">Кликни &amp; Хапни</div>
					</td>
				</tr>
				<tr><td class="pTop10"><a href="http://www.nani.bg" title="Nani.bg - внесете уют"><img src="./images/nani_1_40x40.jpg" alt="Nani.bg" align="left" /></a>
					</td>
					<td><div><a href="http://www.nani.bg" class="link bold" title="Nani.bg - внесете уют">Nani.bg</a></div>
						<div class="size10">Внесете Уют</div>
					</td>
				</tr>
				<tr><td colspan="2"><hr/></td></tr>
			</table>
			
		</td>
	</tr>
	<tr><td colspan="3" style="line-height: 0px; height: 10px;">&nbsp;</td>
	</tr>
	<tr><td colspan="3" class="mainBottom">
		<div>
			<div class="fLeft vtop left10 top10">
				<a class="link" href="./contacts.jsp">Контакти</a><span class="separator">|</span>
				<a class="link" href="./advertisement.jsp">Реклама</a><span class="separator">|</span>
				<a class="link" href="./rights.jsp">Права и задължения</a><span class="separator">|</span>
				<a class="link" href="./help.jsp">Помощ</a><span class="separator">|</span>
				<div class="left">
					<div style="padding-top: 10px;padding-bottom: 10px;">
						<span class="separator" />
						<span class="separator" />
					</div>
				</div>
			</div>
			<div class="fRight vtop right10 top10">
				Copyright © 2007-2007 Менте Софтуер<br>
				<div class="right">Web Design: Пичовете</div>
			</div>
		</div>
		</td>
	</tr>
</table>
</body>

</html>