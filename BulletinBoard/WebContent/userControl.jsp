<%@page language="java" contentType="text/html; charset=UTF-8"
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
<a href = "SignupServlet">ユーザー登録</a>
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
 			<td><c:out value = "${ data.is_stopped }"/></td> 			
 			<td>
 				<form action = "UserEdit" method = "post">			
 				<input type = "hidden" name = "id" value = "${ data.id }"/>
 				<input type = "submit" value = "編集"/>
 				</form>
 			</td>
 		</tr> 
 	</c:forEach> 
	
	</table>
</div>

</body>
</html>