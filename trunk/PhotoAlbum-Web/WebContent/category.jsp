<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Добавяне/Промяна на Категория</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
</head>
<body>
<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
	<tr><td><form name="category" method="POST" action="category">
				<table class="centerMenu top10" cellpadding="0" cellspacing="0" align="center">
					<tr><td class="headerMid" colspan="2">
							<div>Промяна/Добавяне на Категория</div>
						</td>
					</tr>
					<c:choose>
						<c:when test='${param.action == "ADD"}'>
							<tr><td class="leftItem right pTop10">
									<label for="catName">Име на категорията:</label>
								</td>
								<td class="rightItem pTop10">
									<input type="text" name="catName" />
								</td>
							</tr>
						</c:when>
						<c:when test='${param.action == "RENAME"}'>
							<tr><td class="leftItem right pTop10">
									<label for="currentName">Текущото име е:</label>
								</td>
								<td class="rightItem pTop10">
									<input type="text" disabled="disabled" name="currentName" value="${param.currentName}"/>
								</td>
							</tr>
							<tr><td class="leftItem right pTop10">
									<label for="catName">Ново име:</label>
								</td>
								<td class="rightItem pTop10">
									<input type="text" name="catName" />
								</td>
							</tr>
						</c:when>
					</c:choose>
					<tr><td class="leftItem right lh10">
							<a style="cursor: pointer;" onclick="javascript:history.back()">Назад</a>
						</td>
						<td class="rightItem lh10">
							<input class="button" type="submit" name="submit" value="Продължи">
						</td>
					</tr>
					<tr><td class="leftItem right lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
					<tr><td class="bottomMid" colspan="2">&nbsp;</td></tr>
				</table>
				<input type="hidden" name="action" value="${param.action}" />
				<input type="hidden" name="categoryId" value="${param.categoryId}" />
				<input type="hidden" name="parentId" value="${param.parentId}" />
				<input type="hidden" name="parentType" value="${param.parentType}" />
			</form>
		</td>
	</tr>
</table>
</body>
</html>