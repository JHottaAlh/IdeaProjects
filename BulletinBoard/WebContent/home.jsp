<%-- ログイン後のホーム画面 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/submit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/alertSubmit.css" rel="stylesheet" type="text/css" media="screen" />
<link href="./css/main.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/js/commentCountdown.js"/>'></script>
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
<title>ホーム</title>
</head>

<body>
<div id = "contents">
	<div class = "top-menu">
		<div class = "edit">
			<c:if test = "${ loginUser.department_id == 1 }">
			<a href = "usercontrol">ユーザー管理</a>
			</c:if>
		</div>
		<div class = "logout">
			<a href = "logout">ログアウト</a>
		</div>
	</div>
	<%-- エラーメッセージ --%>
	<div class = "errorMessage">
		<c:if test = "${ not empty errorMessages }">
			<div class = "errorMessages">
				<c:forEach items = "${ errorMessages }" var = "message">
					<c:out value = "${ message }"/>
				</c:forEach>
			</div>
			<%-- セッションスコープからエラーメッセージ文を破棄 --%>
			<c:remove var = "errorMessages" scope = "session"/>
		</c:if>
	</div>


	<%-- ログインしている場合にプロフィールを表示 --%>
	<div class = "profile">
		<div class = "topName"><h2><c:out value = "こんにちは、${ loginUser.name }さん"/></h2></div>
			<div class = "login_id">
				@<c:out value = "${ loginUser.login_id }"/><br/>
				<br/>
			</div>
		</div>
	<%-- サイドメニュー --%>
	<div id = "sideMenu">
		<div class = "menu">
			<h1>Menu</h1>
			<span class = "menuLink">
			<a href = "newpost">新規投稿</a>
			</span>
		</div>
		<%-- 記事のソートをするブロック --%>
		<div class = "postSort">
			<form action = "index.jsp" method = "get">
			<div class = "select">
				<p>カテゴリ : </p>
				<select id = "category" name = "category">
				<option value = "">未選択</option>
				<c:forEach items = "${ categories }" var = "categories">
					<option value = "${ categories.category }"
					<c:if test = "${ categories.category == category }">
					selected
					</c:if>>
					<c:out value = "${ categories.category }"/></option>
				</c:forEach>
				</select>
			</div>
			<div class = "sortDate">
				<div class = "oldDate">
					<p>開始日 : </p>
					<input type ="date" class = "date" id = "oldDate" name = "oldDate" value = "${ oldDate }">
				</div>
				<div class = "latestDate">
					<p>終了日 : </p>
					<input type ="date" class = "date" id = "latestDate" name = "latestDate" value = "${ latestDate }">
				</div>
			</div>
			<div class = "send">
				<a href = "#" class = "submit">
					<span class = "submit-inner">
					<input type = "submit" value = "絞込">
					</span>
				</a>
			</div>
			</form>
		</div>
	</div>

	<%-- forEachとかで取得した記事の数だけ表示をループさせる(実装途中) --%>

	<c:forEach items = "${ messages }" var = "message">
		<div id = "main">
			<div class = "post">
				<div class = "title">
					<h1 style="display: inline">Title : </h1><p style="display: inline"><c:out value = "${ message.title }"/></p>
				</div>
			<%-- 情報セキュリティ部または投稿主のみ投稿を削除できるようif文を追加 --%>
			<%-- 社員が投稿した場合その店舗の支店長は投稿削除できるようにもする --%>
			<c:choose>
			<c:when test = "${ (loginUser.department_id == 2 || loginUser.id == message.user_id) ||
				(message.branch_id == loginUser.branch_id && loginUser.department_id == 3) }">
			<%-- 支店長が配下の社員の記事を削除できるようにする --%>
			<form action = "./" method = "post" class = "alertSend">
				<input type = "hidden" name = "id" id = "id" value = "${ message.id }"/>
				<input type = "hidden" name = "user_id" id = "user_id" value = "${ message.user_id }"/>
				<span class = "alertSubmit">	
					<input type = "submit" value = "投稿削除" class = "alertSubmit-inner" onClick = "return postDisp()"/>	
				</span>
			</form>
			</c:when>
			<c:otherwise>
				<div class = "deleteNull">
				</div>
			</c:otherwise>
			</c:choose>
			<div class = "postInfo">
				<div class = "category">カテゴリ : <c:out value = "${ message.category }"/></div>
				<div class = "date"><fmt:formatDate value = "${ message.timed_at }" pattern = "yyyy/MM/dd/ HH:mm"/></div>
				<div class = "name">投稿者 : <p style="display: inline"><c:out value = "${ message.name }"/></p></div>
			</div>
			
			<%-- taglib fn:splitで文字列中の改行で区切ってforEachで一行ずつ表示 --%>
			<div class = "postText">
				<h1 style="display: inline">Text</h1>
			</div>
			<div class = "text">
				<c:forEach items = "${ fn:split(message.text,'
				') }" var = "arr">
				<span class = "Text">
				<c:out value = "${ arr }"/><br/>
				</span>
				</c:forEach>
			</div>
			<%-- コメント投稿フォーム --%>
			<div class = "comForm">
			<form action = "comment" method = "post" class = "comFormtag">
				<div class = "textForm">
				<input type = "hidden" name = "user_id" id = "user_id" value = "${ loginUser.id }"/>
				<input type = "hidden" id = "post_id" name = "post_id" value = "${ message.id }"/>
				<input type = "hidden" id = "branch_id" name = "branch_id" value = "${ loginUser.branch_id }"/>
				<input type = "hidden" id = "department_id" name = "department_id" value = "${ loginUser.department_id }"/>
				<div class = "comTitle">
					<h2 style="display: inline">Comment</h2>
				</div>
				<div class = "commentForm">
				<c:choose>
					<c:when test = "${ post_id == message.id }">
						<textarea name = "text" id = "text" rows="4" cols="60" maxlength="500"><c:out value = "${ text }"/></textarea>
						<div class = "count">0</div>
						<c:remove var = "text" scope = "session"/>
						<c:remove var = "post_id" scope = "session"/>
					</c:when>
					<c:otherwise>
						<textarea name = "text" id = "text" rows="4" cols="60" placeholder="ここにコメントを記入" maxlength="500"></textarea>
						<div class = "count">0</div>
					</c:otherwise>
				</c:choose>
				</div>
				</div>
				<div class = "comSend">
					<a href = "#" class = "submit">
						<span class = "submit-inner">
						<input type = "submit" value = "コメント"/>
						</span>
					</a>
				</div>
			</form>
			</div>
			<%-- コメント表示欄 --%>
			<div class = "commentList">
				<div class = "commentListTitle">
					<h2 style="display: inline">CommentList</h2>
				</div>
				<c:forEach items = "${ comments }" var = "comment">
					<c:if test = "${ comment.post_id == message.id }">
						<div class = "comment">
							<div class = "comDate"><fmt:formatDate value = "${ comment.timed_at }" pattern = "yyyy/MM/dd/ HH:mm"/></div>
							<div class = "comName"><p style="display: inline"><c:out value = "${ comment.name }"/></p></div>
							<div class = "comText">
								<c:forEach items = "${ fn:split(comment.text, '
								') }" var = "arrC">
								<c:out value = "${ arrC }"/><br />
								</c:forEach>
							</div><br/>
						<%-- コメントの削除機能 --%>
						<%-- 情報セキュリティ部または投稿者は削除できる --%>
						<c:if test = "${ (loginUser.department_id == 2 || loginUser.id == comment.user_id) ||
							(comment.branch_id == loginUser.branch_id && loginUser.department_id == 3) }">
						<div  class = "comAlertSend">
							<form action = "commentdelete" method = "post">
							<input type = "hidden" name = "id" id = "id" value = "${ comment.id }"/>
							<input type = "hidden" name = "user_id" id = "user_id" value = "${ comment.user_id }"/>
							<span class = "alertSubmit">	
								<input type = "submit" value = "コメント削除" class = "alertSubmit-inner" onClick = "return commentDisp()"/>	
							</span>
							</form>
						</div>
						</c:if>
					</div>
					</c:if>
				</c:forEach>
			</div>
			</div>
		</div>
	</c:forEach>

</div>
</body>
</html>