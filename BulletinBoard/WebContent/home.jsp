<%-- ログイン後のホーム画面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type = "text/javascript">
<!--
//ユーザー状態切り替え時の確認ダイアログ(post)
function postDisp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('投稿を削除します')){

		location.href = "home";
		return true;

	}
	// 「OK」時の処理終了
	return false;

}
function commentDisp(){

	// 「OK」時の処理開始 ＋ 確認ダイアログの表示
	if(window.confirm('コメントを削除します')){

		location.href = "CommentDelete";
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
<div class = "header">	
	<c:if test = "${ loginUser.department_id == 0 }">
	<a href = "UserControlServlet">ユーザー管理</a>
	</c:if>
	<a href = "LogoutServlet">ログアウト</a>
</div>
<%-- ログインしている場合にプロフィールを表示 --%>
<div class = "profile">	
	<div class = "name"><h2><c:out value = "こんにちは、${ loginUser.name }さん"/></h2></div>
	<div class = "login_id">
		@<c:out value = "${ loginUser.login_id }"/><br/>
		<br/>
	</div>
</div>
	
<%-- 新規投稿へのリンク --%>
<div class = "menu">
	<a href = "NewPostServlet">新規投稿</a>
</div>
<%-- 記事のソートをするブロック --%>
<div class = "postSort">
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
	<form action = "home" method = "get">
	<select id = "category" name = "category">
		<option value = "">未選択</option>
		<option value = "支店情報">支店情報</option>
		<option value = "本部情報">本部情報</option>
		<option value = "共通">共通</option>
	</select>
	<input type ="date" id = "oldDate" name = "oldDate" value = "">
	<input type ="date" id = "latestDate" name = "latestDate" value = "">
	<input type = "submit" value = "絞込">
	</form>
</div>
<%-- forEachとかで取得した記事の数だけ表示をループさせる(実装途中) --%>
<div class = "main">
	<c:forEach items = "${ messages }" var = "message">
		<div class = "post">
			<div class = "title"><c:out value = "${ message.title }"/></div>
			<div class = "category"><c:out value = "${ message.category }"/></div>
			<div class = "text"><c:out value = "${ message.text }"/></div>
			<div class = "name"><c:out value = "${ message.name }"/></div>
			<div class = "date"><fmt:formatDate value = "${ message.timed_at }" pattern = "yyyy/MM/dd/ HH:mm:ss"/></div>
			
			<%-- 情報セキュリティ部または投稿主のみ投稿を削除できるようif文を追加 --%>
			<%-- 社員が投稿した場合その店舗の支店長は投稿削除できるようにもする --%>
			<c:if test = "${ (loginUser.department_id == 1 || loginUser.id == message.user_id) ||
			(message.branch_id == loginUser.branch_id && loginUser.department_id == 2) }">
			<%-- 支店長が配下の社員の記事を削除できるようにする --%>
			<form action = "home" method = "post">
				<input type = "hidden" name = "id" id = "id" value = "${ message.id }"/>
				<input type = "hidden" name = "user_id" id = "user_id" value = "${ message.user_id }"/>
				<input type = "submit" value = "削除" onClick = "return postDisp()"/>
			</form>
			</c:if>
			<%-- コメント表示欄 --%>
			<c:forEach items = "${ comments }" var = "comment">
				<c:if test = "${ comment.post_id == message.id }">
				<div class = "comment">
					------------------------------------------------------------------------
					<div class = "name"><c:out value = "${ comment.name }"/></div>
					<div class = "timed_at"><c:out value = "${ comment.timed_at }"/></div>
					<div class = "text"><c:out value = "${ comment.text }"/></div><br/>
					<%-- コメントの削除機能 --%>
					<%-- 管理者または投稿者は削除できる --%>
					<c:if test = "${ loginUser.department_id == 1 || loginUser.id == comment.user_id }">
					<form action = "CommentDelete" method = "post">
						<input type = "hidden" name = "id" id = "id" value = "${ comment.id }"/>
						<input type = "hidden" name = "user_id" id = "user_id" value = "${ comment.user_id }"/>
						<input type = "submit" value = "削除" onClick = "return commentDisp()"/>
					</form>
					</c:if>
				</div>
				</c:if>
			</c:forEach>
			<%-- コメント投稿フォーム --%>
			<form action = "comment" method = "post">
				<input type = "hidden" name = "user_id" id = "user_id" value = "${ loginUser.id }"/>
				<input type = "hidden" id = "post_id" name = "post_id" value = "${ message.id }"/>
				<textarea name = "text" id = "text" rows="4" cols="60">コメント記入欄。</textarea><br/>
				<input type = "submit" value = "コメント"/>
			</form>
			---------------------------------------------------------------------------------------------
			<br/><br/>
		</div>
	</c:forEach>
</div>




</body>
</html>