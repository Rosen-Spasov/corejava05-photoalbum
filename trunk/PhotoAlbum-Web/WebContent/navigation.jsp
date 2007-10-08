<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			<a href="./user?action=REFRESH">Опресни</a>
		</td>
	</tr>
	<tr><td class="leftItem pTop10">
			<img src="./images/bullet.png" class="bullet" alt="bullet" />
		</td>
		<c:choose>
			<c:when test="${!empty selectedPhoto}">
				<td class="rightItem pTop10">
					<a href="./user?action=LOAD&userId=${selectedUser.userId}&categoryId=${selectedPhoto.category.categoryId}">Нагоре</a>
				</td>
			</c:when>
			<c:otherwise>
				<c:if test="${!empty selectedCategory}">
					<td class="rightItem pTop10">
						<a href="./user?action=LOAD&userId=${selectedUser.userId}&categoryId=${selectedCategory.parentCategory.categoryId}">Нагоре</a>
					</td>
				</c:if>
			</c:otherwise>
		</c:choose>
	</tr>
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