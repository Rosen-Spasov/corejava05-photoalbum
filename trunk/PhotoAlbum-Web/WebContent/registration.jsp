<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table cellpadding="0" cellspacing="0" align="center">
	<tr><td><form name="registrationForm" method="POST" action="registration" onsubmit="validate()">
				<table class="centerMenu top10" cellpadding="0" cellspacing="0" align="center">
					<tr><td class="headerMid" colspan="2">
							<div>Регистрация</div>
						</td>
					</tr>
					<tr><td class="leftItem right pTop10">
							<label for="username">Потребителско име:</label>
						</td>
						<td class="rightItem pTop10">
							<input type="text" name="username" />
						</td>
					</tr>
					<tr><td class="leftItem right pTop10">
							<label for="password">Парола:</label>
						</td>
						<td class="rightItem pTop10">
							<input type="password" name="password" />
						</td>
					</tr>
					<tr><td class="leftItem right pTop10">
							<label for="passwordConfirm">Потвърдете паролата:</label>
						</td>
						<td class="rightItem pTop10">
							<input type="password" name="passwordConfirm" />
						</td>
					</tr>
					<tr><td class="leftItem right pTop10">
							<label for="firstName">Име:</label>
						</td>
						<td class="rightItem pTop10">
							<input type="text" name="firstName" />
						</td>
					</tr>
					<tr><td class="leftItem right pTop10">
							<label for="lastName">Фамилия:</label>
						</td>
						<td class="rightItem pTop10">
							<input type="text" name="lastName" />
						</td>
					</tr>
					<tr><td class="leftItem right lh10">&nbsp;</td>
						<td class="rightItem lh10">&nbsp;</td>
					</tr>
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
			</form>
		</td>
	</tr>
</table>