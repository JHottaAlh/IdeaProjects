<%-- ログイン画面（最初に開くページ） --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/main.css" rel="stylesheet" type="text/css" media="screen" />
<title>ログイン画面</title>
</head>
<body>
<div id = "main-contents">
<div class = "login-main">

<%-- ログインID/パスワード入力フォーム --%>
<div class = "login">
<h1>わったい菜掲示板</h1>
<h2>サインイン</h2>
<%-- エラーメッセージ --%>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<c:forEach items = "${ errorMessages }" var = "message">
			<c:out value = "${ message }"/>
		</c:forEach>
	</div>
	<%-- セッションスコープからエラーメッセージ文を破棄 --%>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>
<form action = "login" method = "post">
	<input type = "text" name = "login_id" id = "login_id" value = "${ login_id }" placeholder="ユーザーID"><br />
	<input type = "password" name = "password" id = "password" placeholder="パスワード"><br />
	<br/>
	<input type = "submit" id = "submit" value = "ログイン"><br />
</form>
</div>
</div>
</div>
</body>
</html>