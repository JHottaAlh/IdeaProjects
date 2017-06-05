<%-- ログイン画面（最初に開くページ） --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/main.css" rel="stylesheet" type="text/css" media="screen" />
<title>ログイン画面</title>
</head>
<body>
<div class = "main-contents">
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

<%-- ログインID/パスワード入力フォーム --%>
<div id = "login">
<h1>ログイン</h1>
<form action = "login" method = "post">
	ログインID : <input type = "text" name = "login_id" id = "login_id" value = "${ login_id }"><br />
	パスワード : <input type = "password" name = "password" id = "password"><br />
	<input type = "submit" value = "ログイン"><br />
</form>
</div>
</div>
</body>
</html>