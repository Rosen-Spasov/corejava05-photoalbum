<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Фото албум</title>
</head>
<body>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
	<head>
	<body>
		<table border="0" width="100%" cellspacing="0" cellpadding="0" 
	style="padding:0px 0px 0px 0px; border:1px solid White;">
	<tr style="background-color: aqua;">
		<td width="124" style="border-right:0px solid White;"><img src="Themes/top-left.gif"></td>
		<td style="text-align:center; border-right: 0px solid White;">
			<span style="font:oblique">Добре дошли във фото албум</span>
		</td>
		<td width="125"><img src="Themes/top-right.gif"></td>
	</tr>
</table>
	
		
		
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Фото албум</title>
		
				<link rel="stylesheet" type="text/css" href="http://img.elmaz.com/style/css/main.css" />
				
	
	
				<td class="mainLeft vtop">
					<!--loginform -->
					
				<form action="" method="post">
					<div>
						<tr>
							<td class="leftLoginTop">&nbsp;</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" class="leftLoginForm">
									<tr>
										<td class="left pTop10 pLeft10"><label for="username">Име: </label></td>
										<td class="left pTop10 pRight10"><input type="text" class="textInput" name="username" id="username" /></td>
									</tr>
									<tr>
										<td class="left pLeft10"><label for="pass">Парола: </label></td>
										<td class="left pRight10"><input type="password" class="textInput" name="pass" id="pass" /></td>
									</tr>
									<tr>
										<td class="right">&nbsp;</td>
										<td class="left">
											<input type="submit" class="button" style="width: 90px;" name="login" id="login" value="Вход" />
										</td>
									</tr>
									<tr>
										<td class="right">&nbsp;</td>
										<td class="left pBottom10">
											<a href="http://www.elmaz.com/index.php?page=lostPassword" class="loginRegister">Забравена парола</a>
											<a href="http://www.elmaz.com/index.php?page=lostPassword" class="loginRegister">Не си регистриран</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
				</div>
				</form>
								<!--left menu -->
				<table cellpadding="0" cellspacing="0" class="leftMenu top10" style="top: auto">
		<tr><td colspan="2" class="headerMin" style="border-bottom: 3px solid #fabc01;">Потребители</td></tr>
		</table>	
<div class="leftMenu top10"  style="width:170px ; height:580px; overflow: scroll" >
	<ol type="disc">
			<% for (int k=0;k<70;k++){ %>
	
				<tr><td><li><a href="">user <%= k %></a></li></td></tr>
				
				<%} %>	
				</ol>
	</div>
</body>
</html>