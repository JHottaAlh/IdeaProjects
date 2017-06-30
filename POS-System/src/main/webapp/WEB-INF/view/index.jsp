<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta charset="utf-8">
		<link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src='<c:url value="/resources/js/Ajax.js"/>'></script>
		<title>register</title>
	</head> 
	<body>
		<f:form modelAttribute = "goods">
			<f:input path = "code" name = "code" value = "${ goods.code }"/>
		</f:form>
		<input type = "button" id = "send" value = "スキャン"/>
		<ul>
			<li><c:out value = "${ goods.maker }"/></li>
			<li><c:out value = "${ goods.goods_name }"/></li>
			<li><c:out value = "${ goods.price }"/></li>
		</ul>
		
	</body>
</html>
