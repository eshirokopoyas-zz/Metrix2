<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Metrix: Главная страница</title>
</head>
<jsp:include page="../fragments/header.jsp"></jsp:include>
<body class="dynamic">
<div class="jumbotron">
<div class="container">
<h1>
Главная страница
</h1>
<p>
Прошу Вас ознакомиться с прототипом системы автоматизации работы с метриками.
</p>
<a href="https://ru.wikipedia.org/wiki/%D0%AD%D0%BA%D0%BE%D0%BD%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D0%BA%D0%B0">
Подробнее об эконометрике
</a>
</div>
</div>
<div class="guides">
<div class="container">
<h2>
Технологии разработки
</h2>
<p>
Заинтересованы технологиями, использованными при разработке? Ниже для Вас приведены примеры использованных технологий.
</p>
<div class="row">
<div class="col-md-4">
<div class="thumbnail">
<a href="http://getbootstrap.com/">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/bootstrap.png">
</a>
</div>
<div class="thumbnail">
<a href="http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/mvc.html">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/Spring-MVC.png">
</a>
</div>
</div>
<div class="col-md-4">
<div class="thumbnail">
<a href="http://hibernate.org/">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/hibernate.png">
</a>
</div>
<div class="thumbnail">
<a href="http://www.oracle.com/technetwork/java/javaee/jsp/index.html">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/jsp.png">
</a>
</div>
</div> 
<div class="col-md-4">
<div class="thumbnail">
<a href="http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/jpa.png">
</a>
</div>
<div class="thumbnail">
<a href="https://jquery.com/">
<img class="compact" src="<%=request.getContextPath()%>/resources/images/JQuery.png">
</a>
</div>
</div>
</div>  
</div>
</div>
<div class="learn-more">
<div class="container">
<div class="row">
<div class="col-md-4">
<h3>
Первое знакомство
</h3>
<p>
Ознакомьтесь со статьёй, подробно описывающей технологии, использованные при разработке платформы.
Также Вы можете ознакомиться с примером работы с одной из экономических метрик.
</p>
<p>
<a href="#">
Статья
</a>
</p>
</div>
<div class="col-md-4">
<h3>
ЦНИИмаш
</h3>
<p>
Ознакомьтесь с предприятием, под эгидой которого осуществлялась разработка данной платформы.
</p>
<p>
<a href="http://www.tsniimash.ru/">
Центральный научно-исследовательский институт машиностроения
</a>
</p>
</div>
<div class="col-md-4">
<h3>
РУДН
</h3>
<p>
Ознакомьтесь с университетом, в котором в рамках научно-исследовательской работы производилась аналитика и разработка данной платформы.
</p>
<p>
<a href="http://www.rudn.ru/">
Российский Университет Дружбы Народов
</a>
</p>
</div>
</div>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>