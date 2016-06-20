<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Metrix: Главная страница</title>
</head>
<jsp:include page="../fragments/header.jsp"></jsp:include>
<body>
<div class="jumbotron">
<div class="container">
<div class="system">
<h1>
Оценка проекта
</h1>
	<spring:url value="/grades/${userid}/${expertid}/${projectid}" var="gradesUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="gradeForm" action="${gradesUrl}">

		<spring:bind path="grade">
			<div class="form-group">
				<label class="col-sm-2 control-label">Значение оценки</label>
				<div class="col-sm-10">
					<form:input path="grade" type="text" class="form-control " id="grade" placeholder="Введите оценку"/>
					<form:errors path="grade" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="criterionIdString">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Критерий</label>
				<div class="col-sm-5">
					<form:select path="criterionIdString" items="${criteriaList}" class="form-control"/>
					<form:errors path="criterionIdString" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>
		
		<div class="form-group">
		<table class="table">
			<tr>
				<td align="left">
				<button type="submit" class="btn-lg btn-primary pull-right" ${criteriaList.isEmpty()?'disabled':''}>Добавить</button>
				</td>
				<td align="right">
				<button type="reset"  onclick="location.href='/second2/${userid}'" class="btn-lg btn-danger pull-right">Вернуться</button>
				</td>
			</tr>
		</table>
		</div>
	</form:form>
	
			<h1>Введённые оценки</h1>

		<table class="table">
			<thead>
				<tr>
					<th>Оценка</th>
					<th>Идентификатор критерия</th>
					<th>Наименование критерия</th>
					<th>Действие</th>
				</tr>
			</thead>

			<c:forEach var="grade" items="${grades}">
				<tr>
					<td>
						${grade.grade}
					</td>
					<td>${grade.criterion.criterion_id}</td>
					<td>${grade.criterion.name}</td>
					<td>
						<spring:url value="/grades/${userid}/${expertid}/${projectid}/${grade.id}/delete" var="deleteUrl" /> 

						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Удалить</button></td>
				</tr>
			</c:forEach>
		</table>
	
</div>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>