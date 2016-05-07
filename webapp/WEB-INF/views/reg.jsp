<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Metrix: Регистрация</title>
</head>
<jsp:include page="../fragments/header.jsp"></jsp:include>
<body>
<div class="jumbotron">
<div class="container">
			
<h1>Регистрация пользователя</h1>

	<spring:url value="/reg" var="regActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${regActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Ф.И.О.</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Введите Ваши фамилию, имя и отчество" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email" placeholder="Введите Email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Пароль</label>
				<div class="col-sm-10">
					<form:password path="password" class="form-control" id="password" placeholder="Введите пароль" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="confirmPassword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Повторный ввод пароля</label>
				<div class="col-sm-10">
					<form:password path="confirmPassword" class="form-control" id="password" placeholder="Повторно введите пароль" />
					<form:errors path="confirmPassword" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="newsletter">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Почтовые уведомления</label>
				<div class="col-sm-10">
					<div class="checkbox">
						<label> <form:checkbox path="newsletter" id="newsletter" />
						</label>
						<form:errors path="newsletter" class="control-label" />
					</div>
				</div>
			</div>
		</spring:bind>

		<spring:bind path="sex">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Пол</label>
				<div class="col-sm-10">
					<label class="radio-inline"> <form:radiobutton path="sex" value="Мужской" /> Мужской
					</label> <label class="radio-inline"> <form:radiobutton path="sex" value="Женский" /> Женский
					</label> <br />
					<form:errors path="sex" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="country">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Страна</label>
				<div class="col-sm-5">
					<form:select path="country" class="form-control">
						<form:option value="NONE" label="--- Выберите страну ---" />
						<form:options items="${countryList}" />
					</form:select>
					<form:errors path="country" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn-lg btn-primary pull-right">Зарегистрировать</button>
			</div>
		</div>
	</form:form>

</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>