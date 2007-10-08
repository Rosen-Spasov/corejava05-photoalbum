<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${!empty selectedCategory}">
		<c:set var="subCategories" value="${selectedCategory.categories}" />
	</c:when>
	<c:otherwise>
		<c:set var="subCategories" value="${selectedUser.categories}" />
	</c:otherwise>
</c:choose>

<table cellpadding="0" cellspacing="0" class="leftMenu top10">
	<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">
			<div>Подкатегории</div>
		</td>
	</tr>
	<c:if test="${empty subCategories}">
		<tr><td class="leftItem pTop10">&nbsp;</td>
			<td class="rightItem pTop10"><b>Няма</b></td>
		</tr>
	</c:if>
	<c:forEach var="category" items="${subCategories}">
		<tr><td class="leftItem pTop10">
				<img src="./images/bullet.png" class="bullet" alt="bullet" />
			</td>
			<td class="rightItem pTop10">
				<a href="./user?action=LOAD&userId=${selectedUser.userId}&categoryId=${category.categoryId}">
					<c:out value="${category.catName}" />
				</a>
			</td>
		</tr>
	</c:forEach>
	<tr><td class="leftItem lh10">&nbsp;</td>
		<td class="rightItem lh10">&nbsp;</td>
	</tr>
	<tr><td colspan="2" class="bottomMin">&nbsp;</td>
	</tr>
</table>