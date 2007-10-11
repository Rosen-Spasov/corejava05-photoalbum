<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Фото албум - Намери снимките, които търсиш!</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
	<script type="text/javascript" src="./js/mainScript.js"></script>
	<script type="text/javascript" src="./js/validation.js"></script>
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
	<c:import url="./header.jsp" />
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
						<a href="./photo?action=REFRESH">Опресни</a>
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
							<a href="./photo?renamePhoto=true">Преименувай</a>
						</td>
					</tr>
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a href="./photo?action=DELETE&photoId=${selectedPhoto.photoId}" >Изтрий снимката</a>
						</td>
					</tr>
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a href="./photo?sendPhoto=true" >Изпрати на приятел</a>
						</td>
					</tr>
					<tr><td class="leftItem lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="bottomMin">&nbsp;</td></tr>
				</table>
			</c:if>
		</td>
		<td colspan="2" class="mainCenterNone vtop">
			<c:if test="${param.sendPhoto == true}">
				<c:import url="sendPhoto.jsp" />
			</c:if>
			<c:if test="${param.showSearch == true}">
				<c:import url="search.jsp" />
			</c:if>
			<c:if test="${param.renamePhoto == true}">
				<c:import url="renamePhoto.jsp" />
			</c:if>
			<table align="center" class="top10">
				<tr><td><img src="./image?photoId=${selectedPhoto.photoId}" style="text-align: center;" alt="${selectedPhoto.phName}" title="${selectedPhoto.phName}"/>
					</td>
				</tr>
			</table>
			<table align="center" cellpadding="0" cellspacing="0" width="400px" style="text-align: left;">
				<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
				<c:choose>
					<c:when test="${!empty selectedPhoto.comments}">
						<tr><td colspan="2"><b>Коментари:</b></td></tr>
						<tr><td colspan="2"><div style="overflow: auto; height: 300px; width: 400px;">
									<c:forEach var="comment" items="${selectedPhoto.comments}">
										<hr/>
										<div style="background-color: #dad2ec;">
											<c:out value="${comment.dateAsString}, ${comment.user.username}:" />
										</div>
										<br/>
										<c:out value="${comment.text}" />
										<c:if test="${!empty loggedUser && loggedUser.userId == selectedUser.userId}">
											<a href="./comment?action=DELETE&commentId=${comment.commentId}" class="link bold">Изтрий</a>
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
			</table>
			<form method="POST" action="./comment?action=ADD">
				<table align="center" cellpadding="0" cellspacing="0" width="400px" style="text-align: left;">
					<c:if test="${!empty loggedUser}">
						<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
						<tr><td colspan="2">
								<textarea rows="8" cols="60" name="text">Тук напишете своя коментар.</textarea>
							</td>
						</tr>
						<tr><td class="lh10" colspan="2">&nbsp;</td></tr>
						<tr><td colspan="2"><input type="submit" class="button" name="addComment" value="Добави" />
							</td>
						</tr>
					</c:if>
				</table>
			</form>
		</td>
	</tr>
	<c:import url="./footer.jsp" />
</table>
</body>
</html>