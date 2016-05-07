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
<body class="dynamic">
<div class="jumbotron">
<div class="container">
<h1>Алгоритм "Кластеризация". Шаг 1.</h1>
 
    <INPUT class="table" type="button" value="Добавить эксперта" onclick="addRow('dataTable')" />
 
    <INPUT class="table" type="button" value="Удалить эксперта" onclick="deleteRow('dataTable')" />
 
    <TABLE class="table table-inverse" id="dataTable" border="1">
        <TR>
            <TD><INPUT type="checkbox" name="chk"/></TD>
            <TD> 1 </TD>
            <TD> <INPUT type="text" /> </TD>
        </TR>
    </TABLE>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>