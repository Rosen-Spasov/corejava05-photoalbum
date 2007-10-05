<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${selectedPhoto.phName}</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
	<script type="text/javascript"></script>
</head>
<body>
<c:set var="allUsers" value="${sessionScope.allUsers}" />
<!-- This is the currently logged in user. -->
<c:set var="loggedUser" value="${sessionScope.loggedUser}" />

<!-- This is the user whose profile we are currently viewing. -->
<c:set var="selectedUser" value="${sessionScope.selectedUser}" />
<c:set var="selectedCategory" value="${sessionScope.selectedCategory}" />
<c:set var="selectedPhoto" value="${sessionScope.selectedPhoto}" />
<c:set var="photoPages" value="${sessionScope.photoPages}" />
<c:set var="pageIndex" value="${sessionScope.pageIndex}" />

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
			<tr><td class="left pLeft10">В момента разглеждате профила на ${selectedUser.username}</td>
				<td class="right pRight10">
					<c:choose>
						<c:when test="${empty loggedUser}">
							<a href="register.jsp">Регистрация</a>
							<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span>
						</c:when>
						<c:otherwise>
							<font style="font-size: 12px;">
								<c:out value='Добре дошъл, ${loggedUser.firstName} ${loggedUser.lastName}!' />
								<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span>
							</font>
							<a href="./login?action=logout">Изход</a>
							<span class="separator"><img src="./images/separator.png" align="middle" /></span>
						</c:otherwise>
					</c:choose>
					<a href="SearchServlet">Търсене</a>
					<span class="separator"><img src="./images/separator.png" align="middle" /></span>
					<a href="./mainPage.jsp?refresh=true">Начало</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr><td class="mainLeft vtop">
			<table cellpadding="0" cellspacing="0" class="leftMenu">
				<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">
						<div>Навигация</div>
					</td>
				</tr>
				<tr><td class="leftItem pTop10">
						<img src="./images/bullet.png" class="bullet" alt="bullet" />
					</td>
					<td class="rightItem pTop10">
						<a href="./mainPage.jsp?refresh=true">Начална страница</a>
					</td>
				</tr>
				<tr><td class="leftItem pTop10">
						<img src="./images/bullet.png" class="bullet" alt="bullet" />
					</td>
					<td class="rightItem pTop10">
						<a>Опресни</a>
					</td>
				</tr>
				<c:if test="${!empty selectedCategory}">
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a href="./user?action=LOAD&userId=${selectedUser.userId}&categoryId=${selectedPhoto.category.categoryId}">Нагоре</a>
						</td>
					</tr>
				</c:if>
				<tr><td class="leftItem pTop10">
						<img src="./images/bullet.png" class="bullet" alt="bullet" />
					</td>
					<td class="rightItem pTop10">
						<a onclick="javascript:history.back()">Назад</a>
					</td>
				</tr>
				<tr><td class="leftItem lh10">&nbsp;</td>
					<td class="rightItem lh10">&nbsp;</td>
				</tr>
				<tr><td colspan="2" class="bottomMin">&nbsp;</td></tr>
			</table>
			<c:if test="${!empty loggedUser && loggedUser.userId == selectedUser.userId}">
				<table cellpadding="0" cellspacing="0" class="leftMenu top10">
					<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">
							<div>Меню</div>
						</td>
					</tr>
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a>Преименувай</a>
						</td>
					</tr>
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a>Изтрий</a>
						</td>
					</tr>
					<tr><td class="leftItem lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="bottomMin">&nbsp;</td></tr>
				</table>
			</c:if>
		</td>
		<td colspan="2" class="mainCenter vtop">
			<img src="${photoPages[pageIndex].absolutePaths[selectedPhoto.path]}" style="text-align: center;" alt="${selectedPhoto.phName}" />
			<table cellpadding="0" cellspacing="0" width="400px" style="text-align: left;">
				<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
				<c:choose>
					<c:when test="${!empty selectedPhoto.comments}">
						<tr><td colspan="2"><b>Коментари:</b></td></tr>
						<tr><td colspan="2"><div style="overflow: auto; height: 300px; width: 400px;">
									<c:forEach var="comment" items="${selectedPhoto.comments}">
										<hr/>
										<div style="background-color: #dad2ec;">
											<c:out value="${comment.dateAsString}, ${comment.sender}:" />
										</div>
										<br/>
										<c:out value="${comment.text}" />
										<c:if test="${!empty loggedUser && loggedUser.userId == selectedUser.userId}">
											<a class="link bold">Изтрий</a>
										</c:if>
									</c:forEach>
								</div>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr><td colspan="2"><b>Няма коментари към тази снимка.</b></td></tr>
					</c:otherwise>
				</c:choose>
				<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
				<tr><td><label for="sentFrom">От:</label></td>
					<td><input type="text" name="sentFrom"/></td>
				</tr>
				<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2">
						<textarea rows="8" cols="60" name="comment">Тук напишете своя коментар.</textarea>
					</td>
				</tr>
				<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2"><input type="submit" class="button" name="addComment" value="Добави" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td colspan="3" style="line-height: 0px; height: 10px;">&nbsp;</td>
	</tr>
	<tr><td colspan="3" class="mainBottom">
		<div>
			<div class="fLeft vtop left10 top10">
				<a class="link" href="http://academy.devbg.org/">Контакти</a><span class="separator">|</span>
				<a class="link" href="Advertisement.jsp">Реклама</a><span class="separator">|</span>
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