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
	<link href="<c:url value = "/resources/css/main.css" />" rel="stylesheet">
	<link href="<c:url value = "/resources/css/signup/signup.css" />" rel="stylesheet">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src='<c:url value="resources/js/main.js"/>'></script>
	<script type="text/javascript" src='<c:url value="resources/js/slide.js"/>'></script>
	<title>Signup</title>
</head>
<body>
	<div id = "top-menu">
		<div class = "CodeRary">
			<h2>CodeRary</h2>
		</div>
	<a class = "btn-1" href="/CSS-JS-Library/">Return</a>
	</div>
	
	<div id="container">
	
	<%-- 参考サイト:http://black-flag.net/jquery/20140527-5155.html --%>
 
<section id="stage1" class="stageBase">
<div class="fieldWrap">
<h1>jQuery OnePage Scroll</h1>
<p>ここは1ページ目</p>
</div><!--/.fieldWrap -->
</section>
 
<section id="stage2" class="stageBase">
<div class="fieldWrap">
<p>ここは2ページ目</p>
</div><!--/.fieldWrap -->
</section>
 
<section id="stage3" class="stageBase">
<div class="stageSlide">
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>ここは3ページ目<br>
    スライドエリア【1】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【2】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【3】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
</div><!--/.stageSlide -->
</section>
 
<section id="stage4" class="stageBase">
<div class="stageSlide">
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【1】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel slideInitial">
    <div class="fieldWrap">
    <p>ここは4ページ目<br>
    スライドエリア【2】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【3】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
</div><!--/.stageSlide -->
</section>
 
<section id="stage5" class="stageBase">
<div class="stageSlide">
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【1】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【2】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel">
    <div class="fieldWrap">
    <p>スライドエリア【3】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
 
    <div class="slidePanel slideInitial">
    <div class="fieldWrap">
    <p>ここは5ページ目<br>
    スライドエリア【4】</p>
    </div><!--/.fieldWrap -->
    </div><!--/.slidePanel -->
</div><!--/.stageSlide -->
</section>
 
</div><!--/#container-->

</body>
</html>