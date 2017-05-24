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
<div class = "header">
	<c:if test = "${ empty loginUser }">
		<a href = "LoginServlet">ログイン</a>
	</c:if>
	<c:if test = "${ not empty loginUser }">
		<a href = "./">ホーム</a>
		<a href = "UserControlServletl">ユーザー管理</a>
		<a href = "LogoutServlet">ログアウト</a>		<%-- 未実装。一旦ホームに戻るように組んである --%>
	</c:if>
</div>
<%-- ログインしている場合にプロフィールを表示 --%>
<c:if test = "${ not empty loginUser }">
	<div class = "profile">	
		<div class = "name"><h2><c:out value = "こんにちは、${ loginUser.name }さん"/></h2></div>
		<div class = "login_id">
			@<c:out value = "${ loginUser.login_id }"/>
		</div>
	</div>
	
	<%-- 新規投稿へのリンク --%>
	<div class = "post">
		<a href = "NewPostServlet">新規投稿</a>
	</div>
</c:if>

<%-- forEachとかで取得した記事の数だけ表示をループさせる --%>
<div class = "post">

</div>

</body>
</html>