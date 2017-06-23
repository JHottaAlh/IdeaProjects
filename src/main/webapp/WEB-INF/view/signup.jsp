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
	<link href="<c:url value = "/resources/css/signup/signup.css" />" rel="stylesheet">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src='<c:url value="resources/js/main.js"/>'></script>
	<script type="text/javascript" src='<c:url value="resources/js/slide.js"/>'></script>
	<script type="text/javascript" src='<c:url value="resources/js/slidemenu.js"/>'></script>
	<title>Signup</title>
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

<div id ="contents">
	
<%-- 参考サイト:http://black-flag.net/jquery/20140527-5155.html --%>
 

<section id="stage1" class="stageBase">
	<div class="fieldWrap">
		<f:form modelAttribute = "signupForm">
			<h1>Login-ID</h1>
			<f:input path = "login_id"/>
				
			<h1>Password</h1>
			<f:input type = "password" path = "password"/>
			
			<h1>User-Name</h1>
			<f:input path = "name"/>
			
			<h1>Mail-Address</h1>
			<f:input path = "mailaddress"/>
			
			<div class = "send">
				<input type = "submit" value = "Create">
			</div>
		</f:form>
	</div><!--/.fieldWrap -->
</section>



 
</div><!--/#container-->

</body>
</html>