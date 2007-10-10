<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Фото албум - Намери снимките, които търсиш!</title>
<link rel="stylesheet" type="text/css" href="./style/main.css" />
<script type="text/javascript" src="./js/mainScript.js"></script>
<script type="text/javascript" src="./js/validation.js"></script>
</head>

<body>
<c:if test="${!empty param.refresh && param.refresh == true}">
	<c:redirect url="/main" />
</c:if>
<c:set var="allUsers" value="${sessionScope.allUsers}" />
<c:set var="loggedUser" value="${sessionScope.loggedUser}" />
<table class="mainTable" cellpadding="0" cellspacing="0" align="center" border="0" height="95%">
	<tr>
		<td colspan="3"><c:import url="./header.jsp" /></td>
	</tr>
	<tr height="*">
		<td class="mainLeft vtop"><c:if test="${empty loggedUser}">
			<c:import url="login.jsp" />
		</c:if>
		<table cellpadding="0" cellspacing="0" class="leftMenu">
			<tr>
				<td colspan="2" class="headerMin"
					style="border-bottom: 3px solid #fabc01;">
				<div>Потребители</div>
				</td>
			</tr>
			<c:if test="${!empty sessionScope.allUsers}">
				<c:forEach var="user" items="${sessionScope.allUsers}">
					<tr>
						<td class="leftItem pTop10"><img src="./images/bullet.png"
							class="bullet" alt="bullet" /></td>
						<td class="rightItem pTop10"><a
							href="./user?action=LOAD&userId=${user.userId}"><c:out
							value="${user.username}" /></a></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td class="leftItem lh10">&nbsp;</td>
				<td class="rightItem lh10">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" class="bottomMin">&nbsp;</td>
			</tr>
		</table>
		<c:import url="advertisement.jsp" /></td>
		<td colspan="2" class="mainCenterNone vtop"><c:if
			test="${param.showRegistration == true}">
			<c:import url="registration.jsp" />
		</c:if> <c:if test="${param.showSearch == true}">
			<c:import url="./search.jsp" />
		</c:if>
		</td>
	</tr>
	<tr>
		<tr>
		<td colspan="3"><c:import url="./footer.jsp" /></td>
	</tr>
	</tr>
</table>
</body>

</html>