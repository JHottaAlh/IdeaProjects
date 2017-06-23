<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value = "/resources/css/index/css-button.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/index/js-button.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/button/hover.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/button/slidemenu.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/index/index.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src='<c:url value="resources/js/slidemenu.js"/>'></script>
	<title>SelectLibrary</title>
</head> 
<body>
<div id = "top-menu">
	<a class = "CodeRary" href="/CSS-JS-Library">CodeRary</a>
</div>

<!-- サイドオープン時メインコンテンツを覆う -->
<div class="overlay" id="js__overlay"></div>

<!-- サイドメニュー -->
<nav class="side-menu">
<ul id = "nav">
	<li><a class = "btn-1" href="signup">Signup</a></li>
	<li><a class = "btn-1" href="login">Login</a></li>
</ul>
</nav>

<!-- 開閉用ボタン -->
<div class="side-menu-btn" id="js__sideMenuBtn">
	<div class = "ellipsis-v">
		<span class = "point top"></span>
		<span class = "point mid"></span>
		<span class = "point bot"></span>
	</div>
</div>

<div id = "contents">
	<div class = "info">
		<h1>
		Choose<br />
		Programming language
		</h1>
	</div>
	<div class = "menu">
		<div id = "CSS">
			<button type = "button" class = "css-raise" name = "css" value = "css" onclick = "location.href = 'css'">
				<h1>CSS</h1>
				<p>ButtonLayout SourceCode</p>
			</button>
		</div>
		<div id = "JS">
			<button type = "button" class = "js-raise" name = "js" value = "js" onclick = "location.href = 'js'">
				<h1>JS</h1>
				<p>script/JQuery SourceCode</p>
			</button>
		</div>
	</div>
</div>
</body>
</html>
