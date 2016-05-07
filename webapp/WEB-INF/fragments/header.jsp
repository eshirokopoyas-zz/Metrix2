<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Metrix: Главная страница</title>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet"/>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet"/>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/clock.css" rel="stylesheet"/>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/images/M.ico">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-2.1.4.min.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/main.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/clock.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.js">
</script>
</head>
<body>
<div class="nav">
<div class="container">
<div class="pull-left">
<ul class="nav nav-tabs">
<li><a href="/metrix">О системе</a></li>
<li><a href="/authors">Об авторах</a></li>
</ul>
</div>
<div class="pull-right">
<ul class="nav nav-tabs">
<li><a href="/reg">Зарегистрироваться</a></li>
<li><a href="/login">Войти</a></li>
<li><a href="/help">Помощь</a></li>
</ul>
</div>
</div>
</div>
<div class="pull-me">
<p class="slide">
Показать/Убрать
</p>
</div>
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
</body>
</html>