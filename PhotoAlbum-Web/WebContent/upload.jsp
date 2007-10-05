<%@ page errorPage="ExceptionHandler.jsp" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Качете снимка във фото албума</title>
	<link rel="stylesheet" type="text/css" href="./style/main.css" />
</head>
<body>
<form method="POST" action="./upload" name="uploadForm" enctype="multipart/form-data">
	<table cellspacing="0" cellpadding="0" align="center" class="top10">
		<tr><td class="headerMid">
				<div>Изберете снимка:</div>
			</td>
		</tr>
		<tr><td><input type="hidden" name="todo" value="upload" />
				<input type="file" name="uploadfile" size="61" accept="image/gif,image/jpg" />
			</td>
		</tr>
		<tr><td align="right">
				<a class="link" onclick="javascript:history.back()">Назад</a>
				<a class="link" href="./mainPage.jsp?refresh=true">Начална страница</a>
				<input type="submit" name="Submit" value="Добави" class="button" />
				<input type="reset" name="Reset" value="Изчисти" class="button" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>
