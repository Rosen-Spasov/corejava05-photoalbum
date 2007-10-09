<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table cellpadding="0" cellspacing="0" border="0" align="center">
	<tr>
		<td class="mainTop" colspan="3">
		<div class="mainTopLogo"><img src="./images/headBG.png" class="noBorder" USEMAP="#link" /> <map name="link">
			<area shape="rect" coords="15,10,225,60" href="mainPage.jsp">
		</map>
			<div class="mainTopBanner"><a href='./main' title=""
				class="headerTexLinks">Дали това не е най-големият сайт за снимки в България?!</a>
			</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="3" class="mainTopMenu">
		<table cellpadding="0" cellspacing="0" class="flex">
			<tr>
				<td class="left pLeft10">Най-големият сайт за снимки в България!</td>
				<td class="right pRight10"><c:choose>
					<c:when test="${empty loggedUser}">
						<a href="./mainPage.jsp?showRegistration=true">Регистрация</a>
						<span class="separator"><img src="./images/separator.png"
							align="middle" style="vertical-align: middle;" /></span>
					</c:when>
					<c:otherwise>
						<font style="font-size: 12px;"> <c:out value='Добре дошъл, ${loggedUser.firstName} ${loggedUser.lastName}' />
						<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span> </font>
						<a href="./login?action=logout">Изход</a>
						<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span>
					</c:otherwise>
				</c:choose> <a href='${pageContext.request.requestURI}?showSearch=true'>Търсене</a>
				<span class="separator"><img src="./images/separator.png" align="middle" style="vertical-align: middle;" /></span> 
				<a href="mainPage.jsp?refresh=true">Начало</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>