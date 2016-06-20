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
<h1>Алгоритм "Кластеризация". Шаг 3. Кластеры проектов.</h1>
<table>
<thead>
</thead>
<tbody>
<tr>
<td>
<img src="/charts/pie/1"/>
</td>
<td>
<img src="/charts/pie/2"/>
</td>
</tr>
<tr>
<td>
<table>
<thead>
</thead>
<tbody>
<c:forEach var="projectModule" items="${cluster1}">
<tr>
<td>
${projectModule.project.project_id} (${projectModule.project.name})
</td>
</tr>
</c:forEach>
</tbody>
</table>
</td>
<td>
<table>
<thead>
</thead>
<tbody>
<c:forEach var="projectModule" items="${cluster2}">
<tr>
<td>
${projectModule.project.project_id} (${projectModule.project.name})
</td>
</tr>
</c:forEach>
</tbody>
</table>
</td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>