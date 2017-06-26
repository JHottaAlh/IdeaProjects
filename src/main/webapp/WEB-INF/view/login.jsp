<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value = "/resources/css/button/hover.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/button/slidemenu.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src='<c:url value="resources/js/slidemenu.js"/>'></script>
	<title>Login</title>
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
	<li><a class = "btn-1" href="login">Login</a></li>
	<li><a class = "btn-1" href="signup">Signup</a></li>
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

<!-- メインコンテンツ -->
<div id = "contents">
	<f:form modelAttribute = "loginForm">
		<f:input path = "login_id" autocomplete="off" />
		<f:input path = "password" type = "password" />
		
		<input type = "submit" value = "LOGIN">
	</f:form>
</div>

</body>
</html>