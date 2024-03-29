<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<c:import url="./head.jsp" />
<body>
<c:if test="${empty sessionScope.initialized || param.refresh == true}">
	<c:redirect url="/main" />
</c:if>
<c:set var="allUsers" value="${sessionScope.allUsers}" />
<c:set var="loggedUser" value="${sessionScope.loggedUser}" />
<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
	<c:import url="./header.jsp" />
	<tr><td class="mainLeft vtop">
			<c:choose>
				<c:when test="${empty loggedUser}">
					<c:import url="login.jsp" />
				</c:when>
				<c:otherwise>
				<table cellpadding="0" cellspacing="0" class="leftMenu top10 vtop">
					<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">
							<div>Меню</div>
						</td>
					</tr>
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a href="./user?action=LOAD&userId=${loggedUser.userId}">Моят профил</a>
						</td>
					</tr>
					<tr><td class="leftItem lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="bottomMin">&nbsp;</td></tr>
				</table>
				<div class="lh10">&nbsp;</div>
				</c:otherwise>
			</c:choose>
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
			<c:import url="advertisement.jsp" />
		</td>
		<td colspan="2" class="mainCenterNone vtop">
			<c:if
				test="${param.showRegistration == true}">
				<c:import url="registration.jsp" />
			</c:if>
			<c:if test="${param.showSearch == true}">
				<c:import url="./search.jsp" />
			</c:if>
		</td>
	</tr>
	<c:import url="./footer.jsp" />
</table>
</body>

</html>