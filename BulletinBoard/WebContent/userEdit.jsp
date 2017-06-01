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
				<option value = "0" 
				<c:if test = "${ data.branch_id == 0 }">
				selected
				</c:if>>本社</option>
				<option value = "1" 
				<c:if test = "${ data.branch_id == 1 }">
				selected
				</c:if>>支店A</option>
				<option value = "2" 
				<c:if test = "${ data.branch_id == 2 }">
				selected
				</c:if>>支店B</option>
				<option value = "3" 
				<c:if test = "${ data.branch_id == 3 }">
				selected
				</c:if>>支店C</option>
			</select>
			<br/>
			<br/>
			部署・役職 :
			<select id = "department_id" name = "department_id">
				<option value = "0" 
				<c:if test = "${ data.department_id == 0 }">
				selected
				</c:if>>人事総務部</option>
				<option value = "1" 
				<c:if test = "${ data.department_id == 1 }">
				selected
				</c:if>>情報セキュリティ部</option>
				<option value = "2" 
				<c:if test = "${ data.department_id == 2 }">
				selected
				</c:if>>店長</option>
				<option value = "3" 
				<c:if test = "${ data.department_id == 3 }">
				selected
				</c:if>>社員</option>
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