<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/submit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/alertSubmit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/main.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/userControl.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/deleteButton.css" rel="stylesheet" type="text/css" media="screen" />
<script type = "text/javascript">
<!--
//ユーザー状態切り替え時の確認ダイアログ(is_stopped)
function disp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('ユーザーの状態を切り替えます')){

		location.href = "IsStoppedServlet";
		return true;

	}
	// 「OK」時の処理終了
	return false;

}
-->
</script>
<title>ユーザー管理</title>
</head>
<body>
<div id = "center-contents">
	<div class = "top-menu">
		<div class = "return">	
		<a href ="./">戻る</a>
		</div>
		<div class = "signupMenu">
		<a href = "signup">ユーザー登録</a>
		</div>
	</div>
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

<div class = "member-list">
	<table class = "table">
	<tr class = "tr_name">
	<td>ログインID</td> <td>名称</td> <td>支店</td> <td>部署・役職</td> <td>状態</td> <td></td>
	</tr>

	<%-- 登録された社員の数だけリストを表示する(未実装) --%>
	<%--ServletでUserControl(Beans)型のuserDataリストに格納したデータをdata変数に格納 --%>
 	<c:forEach items = "${ userData }" var = "data">
 		<tr class = "tr_data">
 			<%-- ログインID --%>
 			<td class = "td_login_id">
 			<c:choose>
 				<c:when test = "${ data.is_stopped == 0 }">
 				<c:out value = "${ data.login_id }"/>
 				</c:when>
 				<c:otherwise>
 				<p style="display: inline">●</p><c:out value = "${ data.login_id }"/>
 				</c:otherwise>
 			</c:choose>
 			</td>
 			
 			<%-- ユーザー名 --%>
 			<td class = "td_name"><c:out value = "${ data.name }"/></td>
 			
 			<%-- 支店名 --%>
 			<td class = "td_branch"><c:out value = "${ data.branch_name }"/></td>
 			
 			<%-- 部署・役職名 --%>
 			<td class = "td_department"><c:out value = "${ data.department_name }"/></td>
 			
 			<%-- ユーザーの状態 --%>
 			<td class = "td_is_stopped">
 			<c:if test = "${ loginUser.id != data.id }">
 				<form action = "isstopped" method = "post">
 					<c:if test = "${ data.is_stopped == 0 }">
 						<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 						<input type = "hidden" name = "is_stopped" id = "is_stopped" value = "${ data.is_stopped }"/>
 						<input type = "submit" class = "button" value = "停止" onClick = "return disp()"/>
 					</c:if>
 					<c:if test = "${ data.is_stopped == 1 }">
 						<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 						<input type = "hidden" name = "is_stopped" id = "is_stopped" value = "${ data.is_stopped }"/>
 						<input type = "submit" class = "resurrection" value = "復活" onClick = "return disp()"/>
 					</c:if>
 				</form>
 			</c:if>
 			</td>
 			
 			<%-- ユーザーの編集 --%>
 			<td class = "td_userEdit">
 			<form action = "useredit" method = "get">
 			<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 			<input type = "submit" class = "editbutton" value = "編集"/>
 			</form>
 			</td>
 		</tr>
 	</c:forEach>

	</table>
</div>
</div>
</body>
</html>