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
<c:forEach var="grade" items="${grades}">
<c:forEach var="expert" items="${grade.experts}">
<h1>
${expert.name}
</h1>
</c:forEach>
</c:forEach>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>