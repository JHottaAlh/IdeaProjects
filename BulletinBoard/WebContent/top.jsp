<%-- ログイン画面（最初に開くページ） --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
<div class = "main-contents">

<%-- IDが間違っていた場合にエラーを吐く --%>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessage">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message">
				<li><c:out value = "${ message }"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>

<%-- ログインID/パスワード入力フォーム --%>
<div class = "main">
	<form action = "TopServlet" method = "post">
		<label for = "id">ログインID : </label>
		<input name = "id" id = "id"/><br />

		<label for = "password">パスワード : </label>
		<input type = "password" name = "password" id = "password"/><br />

		<input type = "submit" value = "ログイン"><br />
	</form>
</div>
</div>
</body>
</html>