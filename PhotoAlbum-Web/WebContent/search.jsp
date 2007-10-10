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
						<form action="./search" name="searchPhoto" method="post" onsubmit="validateSearchName()" >
							<table cellpadding="0" cellspacing="0" class="searchTable" align="center">
								<tr align="center">
									<td><label for="queryString">Име на снимка: </label>
										<input type="text" class="textInput" name="queryString" id="queryString" />
										<input type="submit" class="button" name="submit" value="Търси" />
									</td>
								</tr>
							</table>
							<input type="hidden" name="action" value="SEARCH" />
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