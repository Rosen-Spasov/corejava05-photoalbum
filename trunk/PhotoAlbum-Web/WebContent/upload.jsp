<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form method="POST" action="./upload" name="uploadForm" enctype="multipart/form-data">
	<table cellspacing="0" cellpadding="0" class="centerMenu top10" align="center">
		<tr><td class="headerMid headerBorder" align="left">
				<div>Изберете снимка:</div>
			</td>
		</tr><tr><td class="leftItem rightItem lh10">&nbsp;</td>
		</tr>
		<tr><td class="leftItem rightItem" align="right">
				<input type="hidden" name="todo" value="upload" />
				<input type="file" name="uploadfile" size="54" accept="image/gif,image/jpg" />
			</td>
		</tr><tr><td class="leftItem rightItem lh10">&nbsp;</td>
		</tr>
		<tr><td class="leftItem rightItem" align="right">
				<a class="link" onclick="javascript:history.back()">Назад</a>
				<input type="submit" name="Submit" value="Добави" class="button" />
				<input type="reset" name="Reset" value="Изчисти" class="button" />
			</td>
		</tr>
		<tr><td class="leftItem rightItem lh10">&nbsp;</td>
		</tr>
		<tr><td class="bottomMid">&nbsp;</td>
		</tr>
	</table>
</form>