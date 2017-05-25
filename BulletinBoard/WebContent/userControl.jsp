<%-- ユーザー管理画面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<td>ログインID</td> <td>氏名</td> <td>状態</td>
	</tr>
	
	<%-- 登録された社員の数だけリストを表示する(未実装) --%>
<%-- 	<c:forEach items = "${  }"> --%>
<!-- 		<tr> -->
<%-- 			<td>社員のID</td> --%>
<%-- 			<td>社員の氏名</td> --%>
<%-- 			<td>社員の状態</td> --%>
<!-- 		</tr> -->
<!-- 	</c:forEach> -->
	
	</table>
</div>

</body>
</html>