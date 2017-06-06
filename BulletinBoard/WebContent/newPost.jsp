<%-- 新規投稿画面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
	<form action = "newpost" method = "post">
	<div class = "title">
		件名 : <br/>
		<input type = "text" name = "title" id = "title" value = "${ contents.get(0) }"/><br/>
		<br/>
	</div>
	<div class = "category">
		カテゴリ : <br/>
		<select name = "category" id = "category">
			<option value = "">未選択</option>
			<c:forEach items = "${ categories }" var = "categories">
			<option value = "${ categories.category }"
			<c:if test = "${ contents.get(2) == categories.category }">
			selected</c:if>>
			<c:out value = "${ categories.category }"/></option>
			</c:forEach>
		</select><br/>
		<br/>
	</div>
	<div class = "text">
		本文 : <br/>
		<textarea name="text" rows = "8" cols="50" id = "text"><c:out value = "${ contents.get(1) }"/></textarea><br/>
		<br/>
	</div>
	<input type = "submit" value = "投稿"><br/>
	<a href = "./">戻る</a>
	</form>
</div>

</body>
</html>