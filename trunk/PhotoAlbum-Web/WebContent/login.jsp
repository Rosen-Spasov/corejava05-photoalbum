<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<div class="lh10">&nbsp;</div>
</form>