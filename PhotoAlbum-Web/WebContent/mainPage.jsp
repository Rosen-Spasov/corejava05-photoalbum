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
	<%@ include file="./header.jsp" %>
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
								<a href="./user?action=LOAD&userId=${user.userId}"><c:out value="${user.username}" /></a>
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
		<td colspan="2" class="mainCenterNone vtop">
			<c:if test="${param.showRegistration == true}">
				<%@ include file="registration.jsp" %>
			</c:if>
		</td>
	</tr>
	<%@	include file="./footer.jsp" %>
</table>
</body>

</html>