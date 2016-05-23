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
<h1>Алгоритм "Кластеризация". Шаг 2.</h1>
<c:forEach var="expert" items="${experts}">
<h2>${expert.email}</h2>
<h2>${expert.name}</h2>
<table class="table" >
<thead>
<tr>
<td>
Критерий \ Проект
</td>
<c:forEach var="project" items="${projects}">
<td>
${project.name} (${project.project_id})
</td>
</c:forEach>
</tr>
</thead>
<tbody>
<c:forEach var="criterion" items="${criteria}">
<tr>
<td>
${criterion.name} (${criterion.criterion_id})
</td>
<c:forEach var="project" items="${projects}">
<td>
<input type="text" width="1">
</td>
</c:forEach>
</tr>
</c:forEach>
</tbody>
</table>
</c:forEach>
<img alt="" src="/charts/testpie"/>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>