<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
<title>Metrix: Главная страница</title>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/tables.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tables.js">
</script>
</head>
<jsp:include page="../fragments/header.jsp"></jsp:include>
<body>
<div class="jumbotron">
<div class="container">
<div class="system">
<h1>Алгоритм "Кластеризация". Шаг 1. Настройка системы.</h1>
Необходимо осуществить настройку системы:
<ol>
<li>Указать не менее одного эксперта.</li>
<li>Указать не менее одного проекта для оценки.</li>
<li>Указать не менее одного критерия оценки.</li>
</ol>
<table class="table">
<tr>
<td align="center">
Настройка системы
</td>
<td align="center">
<button class="btn btn-primary" onclick="location.href='/projects/${userid}'">УПРАВЛЕНИЕ ПРОЕКТАМИ</button>
</td>
<td align="center">
<button class="btn btn-primary" onclick="location.href='/experts/${userid}'">УПРАВЛЕНИЕ ЭКСПЕРТАМИ</button>
</td>
<td align="center">
<button class="btn btn-primary" onclick="location.href='/criteria/${userid}'">УПРАВЛЕНИЕ КРИТЕРИЯМИ</button>
</td>
</tr>
<tr>
<td align="center">
Количество
</td>
<td align="center">
${projects_count}
</td>
<td align="center">
${experts_count}
</td>
<td align="center">
${criteria_count}
</td>
</tr>
<tr>
<td colspan="4" align="right">
<button title="Количество элментов не должно быть равно нулю. Продолжите настройку." class="btn btn-primary" onclick="location.href='/second2/${userid}'" ${projects_count*experts_count*criteria_count>0?'':'disabled'} >Далее</button>
</td>
</tr>
</table>
</div>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>