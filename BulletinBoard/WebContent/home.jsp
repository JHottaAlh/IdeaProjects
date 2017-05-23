<%-- ログイン後のホーム画面 --%>

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
<div class = "top-menu">
	<a href = "UserControlServletl">ユーザー管理</a>
	<a href = "LoginServlet">ログイン</a>
	<a href = "">ログアウト</a>		<%-- 一旦ホームに戻るように組んである --%>
</div>

<%-- forEachとかで取得した記事の数だけ表示をループさせる --%>
<div class = "post">

</div>

</body>
</html>