<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Metrix: Авторизация</title>
</head>
<jsp:include page="../fragments/header.jsp"></jsp:include>
<body>
<div class="jumbotron">
<div class="container">

<h1>Авторизация</h1>

	<spring:url value="/login" var="loginActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="loginForm" action="${loginActionUrl}">

		<spring:bind path="user.email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="user.email" class="form-control" id="email" placeholder="Введите Email" />
					<form:errors path="user.email" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="user.password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Пароль</label>
				<div class="col-sm-10">
					<form:password path="user.password" class="form-control" id="password" placeholder="Введите пароль" />
					<form:errors path="user.password" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn-lg btn-primary pull-right">Войти в систему</button>
			</div>
		</div>
	</form:form>
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>