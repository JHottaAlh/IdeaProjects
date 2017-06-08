<%-- ユーザー編集画面（管理者権限のみ閲覧可） --%>

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
<script type = "text/javascript">
<!--
//ユーザー削除時の確認ダイアログ(post)
function userDisp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('ユーザーを削除します')){

		location.href = "userDelete";
		return true;

	}
	// 「OK」時の処理終了
	return false;

}
-->
</script>
<title>ユーザー編集</title>
</head>
<body>
<div id = "contents">
	<div class = "top-menu">
		<div class = "return">	
		<a href ="usercontrol">戻る</a>
		</div>
		<div class = "userDelete">
			<c:forEach items = "${ personalData }" var = "data">
			<form action = "userdelete" method = "post">
			<input type = "hidden" id = "id" name = "id" value = "${ data.id }">
			<input type = "submit" value = "ユーザー削除" onClick = "return userDisp()"/>
			</form>
			</c:forEach>
		</div>
	</div>
	
	<%-- エラーメッセージ --%>
	<c:if test = "${ not empty errorMessages }">
		<div class = "errorMessages">
			<c:forEach items = "${ errorMessages }" var = "message">
				<c:out value = "${ message }"/><br />
			</c:forEach>
		</div>
		<%-- セッションスコープからエラーメッセージ文を破棄 --%>
		<c:remove var = "errorMessages" scope = "session"/>
	</c:if>
	<div class = "title">
		<h1>既存ユーザーの編集</h1>
	</div>
	<div class = "form">
		<c:forEach items = "${ personalData }" var = "data">
		<form action = "usereditsend" method = "post" autocomplete="off">
		<div class = "register">
			<input type = "hidden" name = "id" value = "${ data.id }"/>
			
			<div class = "loginID">
				<p style="display: inline">ログインID</p><span class = "desig">　6文字以上20文字以下　半角英数字のみ</span>
				<input type = "text" id = "login_id" name = "login_id" value = "${ data.login_id }">
			</div>
			
			<div class = "userName">
				<p style="display: inline">ユーザー名</p><span class = "desig">　10文字以下　文字指定無し</span> 
				<input type = "text" id = "name" name = "name" value = "${ data.name }">
			</div>
			
			<div class = "password">
				<p style="display: inline">パスワード</p><span class = "desig">　6文字以上20文字以下　記号を含む全ての半角文字</span> 
				<input type = "password" id = "password" name = "password">
			</div>
			
			<div class = "passwordConfirm">
				<p style="display: inline">パスワード(確認用)</p>
				<input type = "password" id = "password1" name = "password1">
			</div>
			
			<%-- 何も入力されなかったときのための暗号化された元のパスワードを送る --%>
			<input type = "hidden" id = "password2" name = "password2" value = "${ data.password }">
			
			<%-- 管理者自身は権限を編集できないようにする --%>
			
			<c:choose>
			<c:when test = "${ loginUser.id != data.id }">
			<div class = "branch">
				支店 :
				<select id = "branch_id" name = "branch_id">
				<c:forEach items = "${ branchList }" var = "branch">
					<option value = "${ branch.id }"
					<c:if test = "${ data.branch_id == branch.id }">
					selected
					</c:if>>${ branch.name }</option>
				</c:forEach>
				</select>
			</div>
			<div class = "department">
				部署・役職 :
				<select id = "department_id" name = "department_id">
				<c:forEach items = "${ departmentList }" var = "department">
					<option value = "${ department.id }"
					<c:if test = "${ data.department_id == department.id }">
					selected
					</c:if>>${ department.name }</option>
				</c:forEach>
				</select>
			</div>
			</c:when>
			
			<%-- 管理者ならもとの支店、部署・役職情報を送りなおす --%>
			<c:otherwise>
				<input type = "hidden" id = "branch_id" name = "branch_id" value = "${ data.branch_id }">
				<input type = "hidden" id = "department_id" name = "department_id" value = "${ data.department_id }">
			</c:otherwise>
			</c:choose>
		</div>
			<div class = "submit">
				<span class = "submit-inner">
					<input type = "submit" value = "登録">
				</span>
			</div>
		</form>
		</c:forEach>
	</div>
</div>

</body>
</html>