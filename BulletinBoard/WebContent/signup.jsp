<%-- ユーザー登録画面 --%>
<%-- 管理や権限を持つ人のみアクセス可能 --%>

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
<div class = "main-contents">
<%-- 入力に不備があった場合に出力 --%>
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

<%-- 会員登録の入力フォーム --%>
<form action = "SignupServlet" method = "post"><br />
	<label for = "login_id">ID : </label>
	<input name = "login_id" id = "login_id"/><br />
	<br/>
	<label for = "password">パスワード : </label>
	<input type = "password" name = "password" id = "password"/><br />
	<br/>
	<label for = "name">ユーザー名 : </label>
	<input name = "name" id = "name"/><br />
	<br/>
	
	支店 :
	<select id = "branch_id" name = "branch_id">
		<option value = "0">本社</option>
		<option value = "1">支店A</option>
		<option value = "2">支店B</option>
		<option value = "3">支店C</option>
	</select>
	<br/>
	<br/>
	部署・役職 :
	<select id = "department_id" name = "department_id">
		<option value = "0">人事総務部</option>
		<option value = "1">情報セキュリティ部</option>
		<option value = "2">店長</option>
		<option value = "3">社員</option>
	</select>
	<br/>	
	<input type = "submit" value = "登録">
</form>
<a href ="./">戻る</a>
</div>
</body>
</html>