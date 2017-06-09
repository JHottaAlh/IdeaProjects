<%-- 新規投稿画面 --%>

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
<link href="./css/post.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/js/postCountdown.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/titleCountdown.js"/>'></script>
<script type = "text/javascript">
<!-- 
//文字数制限を越えていたらボタンを押せなくする
function wupSubmit(){

  //名前と感想の欄のテキストを変数に代入する
  var title = document.send.elements[0].value.length();
  var text = document.send.elements[1].value.length();
  
  //名前若しくは感想欄のどちらかが空かチェック
  if ( ( title > 50 ) || ( text > 1000 ) )
  {
    //どちらかが当てはまれば、ボタンを押せなくする
    document.send.elements[2].disabled = true;
  }else{
    //両方とも規定文字内であれば、ボタンを押せるようにする
    document.send.elements[2].disabled = false;
  } 
}
 -->
 </script>
<title>新規投稿</title>
</head>
<body>
<div id = "contents">
	<div class = "top-menu">
		<div class = "return">	
		<a href = "./">戻る</a>
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
	<div class = "Post">
		<form action = "newpost" method = "post" name = "send" autocomplete="off">
			<div class = "postDiv">
				<div class = "postH1">
				<h1>Title</h1>
				</div>
				<div class = "postTitle">
				<input type = "text" name = "title" id = "title" value = "${ contents.get(0) }" placeholder="ここに件名を記入" maxlength="50"/>
				<div class = "titlecount">0</div>
				</div>
			</div>
		<div class = "postCategory">
			<div class = "cateH2">
			<h2>Category(カテゴリ)</h2>
			</div>
			<select name = "category" id = "category">
				<option value = "">未選択</option>
				<c:forEach items = "${ categories }" var = "categories">
					<option value = "${ categories.category }"
					<c:if test = "${ contents.get(2) == categories.category }">
					selected</c:if>>
					<c:out value = "${ categories.category }"/></option>
				</c:forEach>
			</select>
		</div>
	<div class = "newPostText">
		<div class = "textH2">
			<h2>Text</h2>
		</div>
		<textarea name="text" rows = "8" cols="50" id = "text" maxlength="1000"><c:out value = "${ contents.get(1) }"/></textarea>
		<div class = "count">0</div>
	</div>
	
	<div class = "postAlertSend">
		<span class = "submit">
		<input type = "submit" value = "投稿" class = "Submit-inner">
		</span>
	</div>
	</form>
</div>
</div>
</body>
</html>