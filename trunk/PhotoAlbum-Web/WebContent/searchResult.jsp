<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<c:set var="foundPhotoPages" value="sessionScope.foundPhotoPages" />
<table class="mainTable" cellpadding="0" cellspacing="0" align="center" height="95%">
	<c:import url="./header.jsp" />
	<tr><td align="center" style="vertical-align: top;"><c:import url="./search.jsp" />
		</td>
	</tr>
	<tr><td><c:choose>
				<c:when test="${!empty foundPhotoPages}">
					<table class="tabsMiddle top10" cellpadding="0" cellspacing="0" align="center">
						<tr><td class="tabs">
								<div class="leftTabStub">&nbsp;</div>
								<div class="tab tabSelected bold">Резултати</div>
								<div class="rightTabStub">&nbsp;</div>
							</td>
						</tr>
						<tr><td class="tabsTableMiddle" align="center">
								<div class="loadingMiddle" style="display: none;">&nbsp;</div>
								<div style="width: 80%;" align="left">
									<c:forEach var="photo" items="${foundPhotoPages[pageIndex].photos}">
										<div class="smallestProfile">
											<div class="smallProfilePicOffline">
												<a href="./photo?action=LOAD&photoId=${photo.photoId}">
													<img class="pic" src="./image?photoId=${photo.photoId}" alt=${photo.phName } title=${photo.phName } />
												</a>
											</div>
											<div class="vipPic">&nbsp;</div>
											<div class="lh17">
												<a href="./photo?action=LOAD&photoId=${photo.photoId}" class="link bold">Номер: ${photo.photoId}</a>
											</div>
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
					</table>
				</c:when>
				<c:otherwise>
					<div style="font-size: 20px; color: #5a3c8b;">Няма намерени снимки с това име.</div>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<c:import url="./footer.jsp" />
</table>
</body>
</html>