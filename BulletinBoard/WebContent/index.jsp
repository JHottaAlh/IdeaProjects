<%-- ログイン画面（最初に開くページ） --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>

<%-- ログインID/パスワード入力フォーム --%>
<form action = "" method = "post">
	ログインID : <input type = "text" name = "id"><br />
	パスワード : <input type = "password" name = "password"><br />
	<input type = "submit" value = "ログイン"><br />
</form>
<%-- 会員登録 --%>
<a href = "">新規登録</a>

</body>
</html>