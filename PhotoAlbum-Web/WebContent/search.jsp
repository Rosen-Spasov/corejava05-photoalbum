<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table cellpadding="0" cellspacing="0" class="flex" align="center" >
	<tr><td class="vtop">
			<table cellpadding="0" cellspacing="0" class="mainSearch top10" align="center">
				<tr><td class="headerBorder headerMid center">
						<div style="width: 400px;">Търси в най-големият сайт за снимки в България</div>
					</td>
				</tr>
				<tr><td class="mainSearchTd">
					<div class="fLeft pLeft10 pTop10">
						<form action="SearchServlet" name="searchPhoto" method="post" onsubmit="validateSearchName()" >
							<table cellpadding="0" cellspacing="0" class="searchTable" align="center">
								<tr align="center">
									<td width="100px">Име на снимка: </td>
									<td width="160px"><input type="text" class="textInput" name="searchName" id="searchName" /></td>
									<td colspan="2" width="130px">
										<input type="submit" class="button" style="width:90px;" name="btnSearch" value="Търси" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					</td>
				</tr>
				<tr><td class="mainSearchTd lh10">&nbsp;</td></tr>
				<tr><td class="bottomMid">&nbsp;</td></tr>
			</table>
		</td>
	</tr>
</table>