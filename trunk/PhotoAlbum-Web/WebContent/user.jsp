<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
<c:set var="photoPages" value="${sessionScope.photoPages}" />
<c:set var="pageIndex" value="${sessionScope.pageIndex}" />
<c:set var="totalPages" value="${sessionScope.totalPages}" />

<table class="mainTable" cellpadding="0" cellspacing="0" align="center" height="95%" border="0">
	<tr>
		<td colspan="3"><c:import url="./header.jsp" /></td>
	</tr>
	<tr height="*"><td class="mainLeft vtop">

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
				<c:if test="${!empty selectedCategory}">
					<tr><td class="leftItem pTop10">
							<img src="./images/bullet.png" class="bullet" alt="bullet" />
						</td>
						<td class="rightItem pTop10">
							<a href="./user?action=LOAD&userId=${selectedUser.userId}&categoryId=${selectedCategory.parentCategory.categoryId}">Нагоре</a>
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
							<c:choose>
								<c:when test="${!empty selectedCategory}">
									<a href="addCategory.jsp?action=ADD&parentType=CATEGORY&parentId=${selectedCategory.categoryId}">
										Добави категория
									</a>
								</c:when>
								<c:otherwise>
									<a href="addCategory.jsp?action=ADD&parentType=USER&parentId=${selectedUser.userId}">
										Добави категория
									</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<c:if test="${!empty selectedCategory}">
						<tr><td class="leftItem pTop10">
								<img src="./images/bullet.png" class="bullet" alt="bullet" />
							</td>
							<td class="rightItem pTop10">
								<a href="renameCategory.jsp?action=RENAME&categoryId=${selectedCategory.categoryId}&currentName=${selectedCategory.catName}">
									Преименувай
								</a>
							</td>
						</tr>
						<tr><td class="leftItem pTop10">
								<img src="./images/bullet.png" class="bullet" alt="bullet" />
							</td>
							<td class="rightItem pTop10">
								<a href="./category?action=DELETE&categoryId=${selectedCategory.categoryId}" onclick="isConfirm()">Изтрий категорията</a>
							</td>
						</tr>
						<tr><td class="leftItem pTop10">
								<img src="./images/bullet.png" class="bullet" alt="bullet" />
							</td>
							<td class="rightItem pTop10">
								<a href="${pageContext.request.requestURI}?addPhoto=true">Добави снимка</a>
							</td>
						</tr>
					</c:if>
					<tr><td class="leftItem lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
					<tr><td colspan="2" class="bottomMin">&nbsp;</td></tr>
				</table>
			</c:if>
			<c:import url="./subCategories.jsp" />
		</td>
		<td colspan="2" class="mainCenter vtop">
			<c:if test="${param.addPhoto == true}">
				<c:import url="./upload.jsp" />
			</c:if>
			<c:if test="${param.showSearch == true}">
				<c:import url="./search.jsp" />
			</c:if>
			<c:if test="${!empty selectedCategory && !empty photoPages}">
			
				<table class="tabsMiddle top10" cellpadding="0" cellspacing="0">
					<tr><td class="tabs">
							<div class="leftTabStub">&nbsp;</div>
							<div class="tab tabSelected bold">${selectedCategory.catName}</div>
							<div class="rightTabStub">&nbsp;</div>
						</td>
					</tr>
					<tr><td class="tabsTableMiddle" align="center">
							<div class="loadingMiddle" style="display:none;">&nbsp;</div>
							<div style="width: 80%;" align="left">
								<c:forEach var="photo" items="${photoPages[pageIndex].photos}">
									<div class="smallestProfile">
										<div class="smallProfilePicOffline">
											<a href="./photo?action=LOAD&photoId=${photo.photoId}">
											<img class="pic" src="./image?photoId=${photo.photoId}" alt=${photo.phName} title=${photo.phName} />
											 <!-- <img class="pic" src="${photoPages[pageIndex].absolutePaths[photo.path]}" alt=${photo.phName} title=${photo.phName} /> -->
											</a>
										</div>
										<div class="vipPic">&nbsp;</div>
										<div class="lh17">
											<a href="./photo?action=LOAD&photoId=${photo.photoId}" class="link bold">Номер: ${photo.photoId}</a>
										</div>
										<c:if test="${!empty loggedUser && loggedUser.userId == selectedUser.userId}">
											<div class="lh17">
												<a href="./photo?action=DELETE&photoId=${photo.photoId}" onclick="isConfirm()" class="link bold">Изтрий</a>
											</div>
										</c:if>
									</div>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr><td class="tabsBottomMid">
							<div class="fLeft left10">
								<c:if test="${pageIndex > 0}">
									<a href="./user?action=PREV_PAGE"><img src="./images/btnLeft.gif" /></a>
								</c:if>
								<c:if test="${pageIndex + 1 < totalPages}">
									<a href="./user?action=NEXT_PAGE"><img src="./images/btnRight.gif" /></a>
								</c:if>
								<span style="vertical-align: 50%">${pageIndex + 1} от ${totalPages}</span>
							</div>
							<c:if test="${!empty loggedUser}">
								<div class="fRight right10">
									<div style="line-height:17px;">
										<a href="${pageContext.request.requestURI}?addPhoto=true" class="link">Добави снимка в настоящата категория &raquo;</a>
									</div>
								</div>
							</c:if>
						</td>
					</tr>
				</table>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="3"><c:import url="./footer.jsp" /></td>
	</tr>
</table>
</body>
</html>