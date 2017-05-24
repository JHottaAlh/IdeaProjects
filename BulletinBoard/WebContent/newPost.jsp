<%-- 新規投稿画面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- エラーメッセージ --%>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message">
				<li><c:out value = "${ message }"/>
			</c:forEach>
		</ul>
	</div>
	<%-- セッションスコープからエラーメッセージ文を破棄 --%>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>
<div class = "Post">
	<form action = "NewPostServlet" method = "post">
	<div class = "title">
		件名 : <br/>
		<input type = "text" name = "title" id = title><br/>
		<br/>
	</div>
	<div class = "category">
		カテゴリ : <br/>
		<select name = "category">
			<option value = "0">支店情報</option>
			<option value = "1">本部情報</option>
			<option value = "2">共通</option>
		</select><br/>
		<br/>
	</div>
	<div class = "text">
		本文 : <br/>
		<textarea name="text" rows = "8" cols="50" id = "text"></textarea><br/>
		<br/>
	</div>
	<input type = "submit" value = "投稿">
	</form>
</div>

</body>
</html>