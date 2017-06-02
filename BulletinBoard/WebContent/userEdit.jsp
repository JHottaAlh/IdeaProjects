<%-- ユーザー編集画面（管理者権限のみ閲覧可） --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Insert title here</title>
</head>
<body>
<div class = "main-contants">
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
	<div class = "title">
		<c:out value = ""/>
	</div>
	<div class = "form">
		<c:forEach items = "${ personalData }" var = "data">
		<form action = "usereditsend" method = "post">
			<input type = "hidden" name = "id" value = "${ data.id }"/>
			ログインID : <input type = "text" id = "login_id" name = "login_id" value = "${ data.login_id }"><br/>
			<br/>
			ユーザー名 : <input type = "text" id = "name" name = "name" value = "${ data.name }"><br/>
			<br/>
			パスワード : <input type = "password" id = "password" name = "password"><br/>
			<br/>
			パスワード(確認用) : <input type = "password" id = "password1" name = "password1"><br/>
			<br/>
			<%-- 何も入力されなかったときのための暗号化された元のパスワードを送る --%>
			<input type = "hidden" id = "password2" name = "password2" value = "${ data.password }">
			支店 :
			<select id = "branch_id" name = "branch_id">
			<c:forEach items = "${ branchList }" var = "branch">
				<option value = "${ branch.id }" 
				<c:if test = "${ data.branch_id == branch.id }">
				selected
				</c:if>>${ branch.name }</option>
			</c:forEach>
			</select>
			<br/>
			<br/>
			部署・役職 :
			<select id = "department_id" name = "department_id">
			<c:forEach items = "${ departmentList }" var = "department">
				<option value = "${ department.id }" 
				<c:if test = "${ data.department_id == department.id }">
				selected
				</c:if>>${ department.name }</option>
			</c:forEach>
			</select>
			<br/>	
			<input type = "submit" value = "登録"><br/>
			<br/>
		</form>
		<form action = "userdelete" method = "post">
			<input type = "hidden" id = "id" name = "id" value = "${ data.id }">
			<input type = "submit" value = "削除" onClick = "return userDisp()"/>
			</form>
		</c:forEach>
	</div>
	<a href ="usercontrol">戻る</a>
</div>

</body>
</html>