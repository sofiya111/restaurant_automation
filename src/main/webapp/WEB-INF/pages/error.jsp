<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<title>Ошибка</title>
<meta charset="utf-8">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
<style type="text/css">
html, body {width:100%;height:100%;overflow:hidden;margin:0px;padding:0px;font-family:'Open Sans',sans-serif;font-size:16px}
body {background:url('<c:url value="/static/error.jpg"/>') top right no-repeat #fff}
.content {width:100%;text-align:center;position:absolute;bottom:10%;left:0px;}
.content a {display:inline-block;text-decoration:none;font-size:14px;color:#2225e4;}
.content a:hover {color:#d02c2a;}
@media only screen and (max-width: 460px), screen and (max-height: 700px) {
.content {position:static;}
.content a {display:block;width:100%;height:100%;position:absolute;top:0px;left:0px;font-size:0px;opacity:0;}
}
</style>
</head>
<body>
<div class="content">
    ${errorMessage}</br>
	<a href="/">Перейти к главной странице</a>
</div>
</body>
</html>