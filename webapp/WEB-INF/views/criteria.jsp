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
<h1>
Управление критериями
</h1>
	<spring:url value="/criteria/${userid}" var="criteriaUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="criterionForm" action="${criterionForm.id>0?updateUrl:criteriaUrl}">

		<spring:bind path="name">
			<div class="form-group">
				<label class="col-sm-2 control-label">Наименование критерия</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Введите наименование" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="criterion_id">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Идентификатор критерия</label>
				<div class="col-sm-10">
					<form:input path="criterion_id" class="form-control" id="criterion_id" placeholder="Введите идентификатор" readonly="${criterionForm.id>0?true:false}" />
					<form:errors path="criterion_id" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
		<table class="table">
			<tr>
				<td align="left">
				<button type="submit" class="btn-lg btn-primary pull-right">${criterionForm.id>0?'Сохранить':'Добавить'}</button>
				</td>
				<td align="right">
				<button type="reset"  onclick="location.href='/first/${userid}'" class="btn-lg btn-danger pull-right">Вернуться</button>
				</td>
			</tr>
		</table>			
		</div>
	</form:form>
	
			<h1>Критерии</h1>

		<table class="table">
			<thead>
				<tr>
					<th>Идентификатор критерия</th>
					<th>Наименование</th>
					<th>Когда создан</th>
					<th>Действие</th>
				</tr>
			</thead>

			<c:forEach var="criterion" items="${criteria}">
				<tr>
					<td>
						${criterion.criterion_id}
					</td>
					<td>${criterion.name}</td>
					<td>${criterion.created}</td>
					<td>
						<spring:url value="/criteria/${userid}/${criterion.id}/delete" var="deleteUrl" /> 
						<spring:url value="/criteria/${userid}/${criterion.id}/update" var="updateUrl" />

						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Редактировать</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Удалить</button></td>
				</tr>
			</c:forEach>
		</table>
	
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>