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
<h1>Алгоритм "Кластеризация". Шаг 2. Оценка проектов.</h1>
<table class="table">
<thead>
<tr>
<th>Эксперт</th>
<th>Проект</th>
<th>Текущих оценок</th>
<th>Необходимо оценок</th>
<th>Статус</th>
</tr>
<tr>
</thead>
<c:forEach var="expertCount" items="${expertsCount}">
<tr>
<td rowspan="${expertCount.projectCounts.size()+1}">
${expertCount.expert.email} (${expertCount.expert.name})
</td>
<td>
</td>
<td>
${expertCount.expertGradeCount}
</td>
<td>
${expertCount.expertGradeCountNeed}
</td>
<td nowrap="nowrap">
<c:choose>
<c:when test="${expertCount.expertGradeCountNeed==expertCount.expertGradeCount}">
<div class="status-ok">
OK
</div>
</c:when>
<c:otherwise>
<div class="status-error">
НЕТ ОЦЕНКИ
</div>
</c:otherwise>
</c:choose>
</td>
</tr>
<c:forEach var="projectCount" items="${expertCount.projectCounts}">
<tr>
<td nowrap="nowrap">
${projectCount.project.project_id} (${projectCount.project.name})
</td>
<td>
${projectCount.projectGradeCount}
</td>
<td>
${projectCount.projectGradeCountNeed}
</td>
<td nowrap="nowrap">
<c:choose>
<c:when test="${projectCount.projectGradeCountNeed==projectCount.projectGradeCount}">
<div class="status-ok">
OK
</div>
</c:when>
<c:otherwise>
<div class="status-error">
НЕТ ОЦЕНКИ
</div>
</c:otherwise>
</c:choose>
</td>
<td nowrap="nowrap">
<button onclick="location.href='/grades/${userid}/${expertCount.expert.id}/${projectCount.project.id}'">
${projectCount.projectGradeCountNeed==projectCount.projectGradeCount?'ИЗМЕНИТЬ ОЦЕНКУ':'ОЦЕНИТЬ'}
</button>
</td>
</tr>
</c:forEach>
</c:forEach>
<tr>
<td colspan="3" align="left">
<button class="btn btn-primary" onclick="location.href='/first/${userid}'">Вернуться</button>
</td>
<td colspan="3" align="right">
<button class="btn btn-primary" onclick="location.href='/third/${userid}'" ${next?'':'disabled'}>Далее</button>
</td>
</tr>
</table>
</div>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>