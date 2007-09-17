<%-- jsf:pagecode language="java" location="/src/pagecode/OldMainPage.java" --%><%-- /jsf:pagecode --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="photoalbum.entities.User"%>
<%@page import="photoalbum.PhotoAlbumManipulator"%>
<%@page import="java.util.Set"%>
<%@page import="photoalbum.entities.Category"%>
<%@page import="photoalbum.entities.Photo"%>
<html>
<head>
	<title>Фото албум - Намери снимките, които търсиш!</title>
	<link rel="stylesheet" type="text/css" href="Themes/main.css" />
	<script type="text/javascript"></script>
</head>
<body onload="">
	<% User userLogin = (User)session.getAttribute("login"); %>
	<table class="mainTable" cellpadding="0" cellspacing="0" align="center">
		<tr><td colspan="3" class="mainTop"></td></tr>
		<tr><td colspan="3" class="mainTopMenu">
			<table cellspacing="0" class="flex">
				<tr><td class="left pLeft10">Най-големият сайт за снимки в България! </td><td class="right">
						<% if (userLogin == null) { %>
						<a href="register.jsp">Регистрация</a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% }else {  %>
						<a href="ShowUser.jsp"><%= "Добре дошъл " + userLogin.getFirstName() + " " + userLogin.getLastName() %></a><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% session.setAttribute("user",userLogin); %>
						<a href="ExitServlet">Изход</a><span class="separator"><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<% } %>
						<a href="SearchServlet">Търсене</a><span class="separator"><span class="separator"><img src="img/separator.png" align="absmiddle" /></span>
						<a href="Help.jsp">Помощ</a>
					</td></tr></table>
			</td></tr>
		 <tr><td class="mainLeft vtop">
		 	
					<!--loginform -->
			<form action="LoginServlet" method="post">
				<table cellpadding="0" cellspacing="0">
					<tr><td class="leftLoginTop">&nbsp;</td></tr>
					<tr><td><table cellpadding="0" cellspacing="0" class="leftLoginForm">
							<% if (userLogin == null) { %>
								<tr><td class="left pTop10 pLeft10"><label for="userName">Име: </label></td>
									<td class="left pTop10 pRight10"><input type="text" class="textInput" name="username" id="username" /></td>
								</tr>
								<tr><td class="left pLeft10"><label for="pass">Парола: </label></td>
									<td class="left pRight10"><input type="password" class="textInput" name="pass" id="pass" /></td>
								</tr><td class="right">&nbsp;</td>
								<% if (session.getAttribute("errors")!=null){
									String[] errors = (String[])session.getAttribute("errors");
									for (String err: errors){
										if (err!=null){%>
								<tr><td colspan="2"><%= err %></td></tr>
								<%} } }%>
									<tr><td></td>
									<td class="left">
									<input type="submit" class="button" style="width: 90px;" name="login" id="login" value="Вход" />
									</td>
								</tr>
							<% } %>
							</table>
						</td>
					</tr>
					<tr><td class="leftLoginBottom">&nbsp;</td></tr>
				</table>
			</form>
<!--left menu -->
			<table class="leftMenu top10" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="2"><div>Потребители</div></td></tr>
				<%	User[] allUsers = (User[]) session.getAttribute("allUser");
					if (allUsers != null) {
						for (User user: allUsers) {
				%>
				<tr>
					<td class="fill" width="10px"><img class="bullet" src="./img/bullet.png" /></td>
					<td class="fill"><a href="SearchServlet?searchName=<%= user.getUsername() %>"><%= user.getUsername() %></a></td>
				</tr>
				<%		}
					}
				%>
				<tr><td class="bottom" colspan="2"></td></tr>
			</table>
<!--left menu end -->

		<%	String ref = (String)session.getAttribute("ref");
			if (ref == null){
			response.sendRedirect("PageServlet");
		
			} else {
				session.setAttribute("ref", null);
				String pages = (String)session.getAttribute("pag");
				if (session.getAttribute("nextPage")!=null){
				Integer nextPage = Integer.valueOf((Integer)session.getAttribute("nextPage"));
				int nowPage = 1;
				if (session.getAttribute("nowPage")!=null){
			//	out.println(session.getAttribute("nowPage"));
				nowPage = Integer.parseInt(session.getAttribute("nowPage").toString());
				}
				String[] photoId = ((String[])session.getAttribute("photoId"));
				String[] photoComment=((String[])session.getAttribute("photoComment"));
				String[] pathAll=(String[])session.getAttribute("pathAll");
				String[] photoName=(String[])session.getAttribute("photoName");
				ref = null;
				int count = 0;
				
		%>

			<div class="kare">
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td class="headerBorder headerMin">
							<div style="width: 150px;">Mясто за реклама</div>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr>
						<td>
							<a href="http://academy.devbg.org/" target="_blank" title="Н А Р С"><img src="img/logo.gif" alt="Национална академия за разработка на софтуер" align="left" width="170"/></a>
						</td></tr>
						<tr>
						<td class="pLeft10">
							<div><a href="http://academy.devbg.org/" class="bold purple2" target="_blank" title="Национална академия за разработка на софтуер">НАРС</a></div>
							<div class="size10"><a href="http://academy.devbg.org/" class="black" target="_blank" title="Обучение и професионална реализация в НАРС">Обучение и професионална реализация</a></div>
						</td>
					</tr>
				</table>
				
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td>
							<a href="http://www.amam.bg/" target="_blank" title="кликни &amp; хапни"><img src="img/kare_amam.jpg" alt="кликни &amp; хапни" align="left" /></a>
						</td>
						<td class="pLeft10">
							<div><a href="http://www.amam.bg/" class="bold purple2" target="_blank" title="кликни &amp; хапни">Amam.bg</a></div>
							<div class="size10"><a href="http://www.amam.bg/?ad=004;00;03" class="black" target="_blank" title="кликни &amp; хапни">кликни &amp; хапни</a></div>
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top10">
					<tr><td>
							<a href="http://www.amam.bg/" target="_blank" title="Nani.bg - внесете уют"><img src="img/nani_1_40x40.jpg" alt="Nani.bg" align="left" /></a>
						</td>
						<td class="pLeft10">
							<div><a href="http://www.nani.bg" class="bold purple2" target="_blank" title="Nani.bg - внесете уют">Nani.bg</a></div>
							<div class="size10"><a href="http://www.nani.bg" class="black" target="_blank" title="Nani.bg - внесете уют">внесете уют</a></div>
						</td>
					</tr>
				</table>
			</div>
			<hr />
			<div>
				<table cellpadding="0" cellspacing="0" class="top5">
					<tr>
						
					</tr>
				</table>
				<table cellpadding="0" cellspacing="0" class="top5">
					<tr>
					</tr>
				</table>
			</div>	</td>				
<!-- search banner -->
		
			<td colspan="2" class="mainCenterNone vtop">
				<table cellpadding="0" cellspacing="0" class="flex">
					<tr><td class="vtop">
						<table cellpadding="0" cellspacing="0" class="mainSearch top10">
							<tr><td class="headerBorder headerMid center">
									<div style="width: 400px;">Търси в най-големият сайт за снимки в България</div>
								</td>
							</tr>
							<tr><td class="mainSearchTd">
								<div class="fLeft pLeft10 pTop10">
									<form action="SearchServlet" method="post">
										<table cellpadding="0" cellspacing="0" class="searchTable">
											<tr><td colspan="2">търсенето става по име и/или категория</td></tr>
											<tr><td>Име: </td>
												<td><input type="text" class="textInput" name="searchName" id="searchName" /></td>
											</tr>
											<tr><td>Категория: </td>
												<td><input type="text" class="textInput" name="category" id="category" /></td>
											</tr>
											<tr><td colspan="2">
												<input type="submit" class="button" style="width:90px;" name="btnSearch" value="Търси" />
												</td>
											</tr>
										<%	String search = (String) request.getAttribute("search");
											if (search != null) {
										%>	<tr><td><%= search %></td></tr>
										<%	} %>
										</table>
									</form>
								</div>
								<div class="fRight top10 right10">
									<div class="stats">
										<div class="bold">Регистрации: </div>
										<div>Общо: <span class="bold"><%= allUsers.length %></span></div>
								
									</div>
									<div class="center" style="line-height:17px;">
										<a href="register.jsp" class="link bold">Регистрирай се</a>
									</div>
								</div>
								</td>
							</tr>
							<tr>
								<td class="mainSearchTd lh10">&nbsp;</td>
							</tr>
							<tr>
								<td class="bottomMid">&nbsp;</td>
							</tr>
						</table>
				
					<table class="tabsMiddle top10" cellpadding="0" cellspacing="0">
						<tr>
					<%	for (int k = 0; k < photoId.length;k++) {
							if (photoName[k] != null){
								if (count == 3) {
									count = 1;
					%>
						</tr>
						<tr>
							<%	} else {
									count++;
								}
							%>
							<td class="tabsTableMiddle">
								<div class="smallestProfile">
										<div class="smallProfilePicOnline">
											<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>"><img src="<%= pathAll[k] %>" height="100" alt="" title="<%= photoName[k] %>" /></a>
										</div>
										
										<div class="lh17">
											<a href="fullScreen.jsp?pic=<%= Integer.parseInt(photoId[k]) %>" class="link bold">Виж на цял екран</a>
											<% String[] viewName = photoName[k].split("[.]");
											if (viewName[0].length()>20){
												viewName[0] = viewName[0].substring(0,19);}%>
											<%= viewName[0] %>
										
										</div>
										<div class="lh17">Коментари <span class="bold"><%= photoComment[k] %></span></div>
	
									</div>
					<%		}
						 }
					%>							
						<tr>
							<td class="tabsBottomMid">
								<div class="fLeft left10">
								<% int prev = nowPage-1;
									int next = nowPage+1; %>
									<a href="PageServlet?page=<%= prev%>" ><img src="img/btnLeft.gif" align="absmiddle" /></a>
									<a href="PageServlet?page=<%= next%>" ><img src="img/btnRight.gif" align="absmiddle" /></a>
									<span id="menPage"><%= nowPage %></span> от <span id="menTotalPages"><%= pages %></span>
								</div>
								<div class="fRight right10">
									<div style="line-height:17px;">
										
									</div>
								</div>
							</td>
						</tr>
					</table>
			<%} }%>			
					</td>
		<td class="vtop pLeft20">
							<div class="right right10 advertisement"><img src="http://img.elmaz.com/style/img/advArrow.png" /> реклама</div>
	
			<div>
		
<script type="text/javascript"
  src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
				</div>
							<div class="weddings top10" style="border: 1px solid white;">
				<div class="top10 left10">
					<img src="http://img.elmaz.com/style/img/num1.gif" align="absmiddle" />
					<a href="SearchServlet" class="link yellow">Намери</a> своята снимка или на твои приятел сред 666 регистрирани потребители
				</div>
				<div class="left10 top10">
					<img src="http://img.elmaz.com/style/img/num2.gif" align="absmiddle" />
					<a href="register.jsp" class="link yellow">Регистрирай се</a> и си направи профил напълно безплатно
				</div>
				<div class="left10 top10">
					<img src="http://img.elmaz.com/style/img/num3.gif" align="absmiddle" />
					Срещни стари познати ;-)
				</div>
				
			</div>
						<div class="specialLinks top10">
				<div class="headerSmall rightSquare" style="border-bottom:3px solid #6cce00;padding-top: 3px;height:19px;">
					<div>Посетете</div>
				</div>
				<div class="top10 left10 right10 size10"><a target="_blank" class="link purple2 lh17" href="http://www.news.bg" title="Вижте най новите новини!">News.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.zazz.bg" title="Забавни видео и снимки">Sibir.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.garibaldicafe.net" title="Гарибалди Кафе">Гарибалди Кафе</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.fresh.bg" title="Fresh.bg">Fresh.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.amam.bg" title="Amam.bg">Amam.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.slon.bg" title="Slon.bg">Slon.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.yoosms.com?affid=3" title="gsm мелодии и картинки">gsm мелодии и картинки</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.helpos.com" title="Реферати">Реферати</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.kefche.com" title="Kefche.com">Kefche.com</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.comfort.bg/" title="Comfort.bg">Comfort.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://ide.li" title="Ide.li">Ide.li</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.e-televizor.com" title="Онлайн телевизия">Онлайн телевизия</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://bgtop.net/in.php/1029159402" title=".: BG TOP 100 :.">.: BG TOP 100 :.</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.insurance.bg/products/index.php?cat=products&amp;OnlineRequests=1&amp;RequestId=10&amp;ProductId=11" title="Знаете ли какво е ГО?">Знаете ли какво е ГО?</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.winner.bg" title="Футбол Winner.bg">Футбол Winner.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.tialoto.bg" title="tialoto.bg">tialoto.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.yogakriya.com" title="Йога">Йога</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.referati.com" title="Курсови работи от Referati.com">Курсови работи от Referati.com</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.bulcards.com" title="Картички">Картички</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.astrohoroscope.info" title="Хороскопи">Хороскопи</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.moetoparty.com" title="Моето Парти">Моето Парти</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.liternet.bg" title="liternet.bg">liternet.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.gotvetesmen.com/" title="Рецепта за деня">Рецепти</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://ohoboho.com" title="ohoboho.com">ohoboho.com</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.travel.bg/" title="почивки, екскурзии, хотели, заведения, транспорт">TRAVEL туризъм</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://electron.bg" title="electron.bg">electron.bg</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://zaedno.de/" title="Заедно">Заедно</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.bgstroitel.com/" title="Строителство, Обзавеждане, Имоти">Строителство</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.bgtraffic.com/" title="Интернет каталог">Интернет каталог</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.homepage.bg/" title="лична страница, избрани линкове, лични линкове, собствени настройки, начална страница, избери сам">homepage</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.yes.bg/" title="Yes.bg - Полезен всеки ден">Yes.bg - Полезен всеки ден</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://spodeli.net/" title="Spodeli.net - Сайтът за споделени истории">Spodeli.net</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.photoshop-bg.com/" title="Реалността в нереалното!">Photoshop-BG</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.fortegames.com" title="Онлайн белот и сантасе">Онлайн Белот</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.softvisia.com" title="SoftVisia">SoftVisia</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.bgstuff.net" title="Новини, фитнес, диет, отслабване игри, вицове, хороскопи, филми, онлайн ТВ, радио.">BGSTUFF.NET</a> <span class="purple separator">|</span> <a target="_blank" class="link purple2 lh17" href="http://www.bgtelevizia.com/" title="Online Bulgarian Television">Онлайн Телевизия</a> </div>
			</div>
		</td>
	</tr>
</table>
		</td>
							</tr>
						<tr>
				<td colspan="3" style="line-height: 0px; height: 10px;">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" class="mainBottom">
					
<div>
	<div class="fLeft vtop left10 top10">
		
		<a class="link" href="http://academy.devbg.org/">За контакти</a><span class="separator">|</span>
		<a class="link" href="Advertisement.jsp">За реклама</a><span class="separator">|</span>
				<a class="link" href="Dot.jsp">Права и задължения</a><span class="separator">|</span>
		<a class="link" href="Help.jsp">Помощ</a><span class="separator">|</span>
		<div class="left"><div style="padding-top: 10px;padding-bottom: 10px;">
	
	<span class="separator"></span>

	<span class="separator"></span>

	</div></div>
	</div>
	<div class="fRight vtop right10 top10">
		Copyright © 2007-2007 менте софтуер<br />
		<div class="right">Web Design: НАРС</div>
	</div>
	
</div>				</td>
			</tr>
		</table>
</body>
</html>