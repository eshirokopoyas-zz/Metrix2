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
Управление проектами
</h1>
	<spring:url value="/projects/${userid}" var="projectsUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="projectForm" action="${projectForm.id>0?updateUrl:projectsUrl}">

		<spring:bind path="name">
			<div class="form-group">
				<label class="col-sm-2 control-label">Наименование проекта</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Введите наименование" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="project_id">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Идентификатор проекта</label>
				<div class="col-sm-10">
					<form:input path="project_id" class="form-control" id="project_id" placeholder="Введите идентификатор" readonly="${projectForm.id>0?true:false}" />
					<form:errors path="project_id" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
		<table class="table">
			<tr>
				<td align="left">
				<button type="submit" class="btn-lg btn-primary pull-right">${projectForm.id>0?'Сохранить':'Добавить'}</button>
				</td>
				<td align="right">
				<button type="reset"  onclick="location.href='/first/${userid}'" class="btn-lg btn-danger pull-right">Вернуться</button>
				</td>
			</tr>
		</table>
		</div>
	</form:form>
	
			<h1>Проекты</h1>

		<table class="table">
			<thead>
				<tr>
					<th>Идентификатор проекта</th>
					<th>Наименование</th>
					<th>Когда создан</th>
					<th>Действие</th>
				</tr>
			</thead>

			<c:forEach var="project" items="${projects}">
				<tr>
					<td>
						${project.project_id}
					</td>
					<td>${project.name}</td>
					<td>${project.created}</td>
					<td>
						<spring:url value="/projects/${userid}/${project.id}/delete" var="deleteUrl" /> 
						<spring:url value="/projects/${userid}/${project.id}/update" var="updateUrl" />

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