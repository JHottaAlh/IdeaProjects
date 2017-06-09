<%-- ユーザー登録画面 --%>
<%-- 管理や権限を持つ人のみアクセス可能 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/submit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/alertSubmit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/main.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/signup-userEdit.css" rel="stylesheet" type="text/css" media="screen" />
<title>ユーザー登録</title>
</head>
<body>
<div id = "contents">
	<div class = "top-menu">
		<div class = "return">	
		<a href ="usercontrol">戻る</a>
		</div>
	</div>
	<%-- エラーメッセージ --%>
	<c:if test = "${ not empty errorMessages }">
		<div class = "errorMessages">
			<c:forEach items = "${ errorMessages }" var = "message">
				<c:out value = "${ message }"/><br/>
			</c:forEach>
		</div>
		<%-- セッションスコープからエラーメッセージ文を破棄 --%>
		<c:remove var = "errorMessages" scope = "session"/>
	</c:if>
	<div class = "title">
		<h1>新規ユーザーを追加</h1>
	</div>
	<div class = "form">
		<form action = "signup" method = "post" autocomplete="off">
		<div class = "register">
			<div class = "loginID">
				<p style="display: inline">ログインID</p><span class = "desig">　6文字以上20文字以下　半角英数字のみ</span>
				<input type = "text" id = "login_id" name = "login_id" value = "${ info.getLogin_id() }">
			</div>
			<div class = "userName">
				<p style="display: inline">名称</p><span class = "desig">　10文字以下　文字指定無し</span>
				<input type = "text" id = "name" name = "name" value = "${ info.getName() }">
			</div>
			<div class = "password">
				<p style="display: inline">パスワード</p><span class = "desig">　6文字以上20文字以下　記号を含む全ての半角文字</span>
				<input type = "password" id = "password" name = "password">
			</div>
			<div class = "passwordConfirm">
				<p style="display: inline">パスワード(確認用)</p>
				<input type = "password" id = "password1" name = "password1">
			</div>
			
			<div class = "branch">
				支店 :
				<select id = "branch_id" name = "branch_id">
					<option value = "999">未選択</option>
					<c:forEach items = "${ branchList }" var = "branch">
					<option value = "${ branch.id }"
					<c:if test = "${ branch.id == info.getBranch_id() }">selected</c:if>>${ branch.name }</option>
					</c:forEach>
				</select>
			</div>
			
			<div class = "department">
				部署・役職 :
				<select id = "department_id" name = "department_id">
					<option value = "999">未選択</option>
					<c:forEach items = "${ departmentList }" var = "department">
					<option value = "${ department.id }"
					<c:if test = "${ department.id == info.getDepartment_id() }">selected</c:if>>${ department.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
			
			<c:remove var = "info" scope = "session"/>
			
			<div class = "submit">
				<span class = "submit-inner">
					<input type = "submit" value = "登録">
				</span>
			</div>
		</form>
	</div>
	
</div>
</body>
</html>