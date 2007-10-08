<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
	<c:import url="./header.jsp" />
	<c:set var="searchPhotos" value="${sessionScope.searchPhotos}" />

	<c:if test="${!empty searchPhotos && !empty photoPages}">
	<tr><td>
				<table class="tabsMiddle top10" cellpadding="0" cellspacing="0" align="center">
				
					<tr><td class="tabs">
							<div class="leftTabStub">&nbsp;</div>
							<div class="tab tabSelected bold">резултати</div>
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
									<a href="./search?action=PREV_PAGE"><img src="./images/btnLeft.gif" /></a>
								</c:if>
								<c:if test="${pageIndex + 1 < totalPages}">
									<a href="./search?action=NEXT_PAGE"><img src="./images/btnRight.gif" /></a>
								</c:if>
								<span style="vertical-align: 50%">${pageIndex + 1} от ${totalPages}</span>
							</div>
							
						</td>
					</tr>
				</table></td></tr>
			</c:if>
			
		<c:import url="./footer.jsp" />
	</table>
</body>
</html>