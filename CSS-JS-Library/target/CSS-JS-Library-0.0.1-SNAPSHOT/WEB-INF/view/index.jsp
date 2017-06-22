<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="utf-8">
	<link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/index/index.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/index/css-button.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/index/js-button.css" />" rel="stylesheet">
	<title>SelectLibrary</title>
</head> 
<body>
<div id = "top-menu">
	<div class = "CodeRary">
		<h2>CodeRary</h2>
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
