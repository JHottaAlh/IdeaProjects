<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Insert title here</title>
</head>
<body>
<div class = "top-menu">
<a href = "signup">ユーザー登録</a>
<a href = "./">戻る</a>
</div>

<div class = "member-list">
	<table border="1">
	<tr>
	<td>ログインID</td> <td>氏名</td> <td>状態</td> <td>編集</td>
	</tr>
	
	<%-- 登録された社員の数だけリストを表示する(未実装) --%>
	<%--ServletでUserControl(Beans)型のuserDataリストに格納したデータをdata変数に格納 --%>
 	<c:forEach items = "${ userData }" var = "data"> 
 		<tr>
 			<td><c:out value = "${ data.login_id }"/></td>
 			<td><c:out value = "${ data.name }"/></td>
 			<td>
 			<c:if test = "${ loginUser.id != data.id }">
 				<form action = "isstopped" method = "post">
 					<c:if test = "${ data.is_stopped == 0 }">
 						<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 						<input type = "hidden" name = "is_stopped" id = "is_stopped" value = "${ data.is_stopped }"/>
 						<input type = "submit" value = "停止" onClick = "return disp()"/>
 					</c:if>
 					<c:if test = "${ data.is_stopped == 1 }">
 						<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 						<input type = "hidden" name = "is_stopped" id = "is_stopped" value = "${ data.is_stopped }"/>
 						<input type = "submit" value = "復活" onClick = "return disp()"/>
 					</c:if>
 				</form>
 			</c:if>
 			</td> 			
 			<td>
 			<c:if test = "${ loginUser.id != data.id }">
 				<form action = "useredit" method = "post">			
 				<input type = "hidden" name = "id" id = "id" value = "${ data.id }"/>
 				<input type = "submit" value = "編集"/>
 				</form>
 			</c:if>
 			</td>
 		</tr> 
 	</c:forEach> 
	
	</table>
</div>

</body>
</html>