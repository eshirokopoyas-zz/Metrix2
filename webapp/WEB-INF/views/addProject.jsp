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
<body class="dynamic">
<div class="jumbotron">
<div class="container">
<h1>
Проекты
</h1>
 <h2>Create New</h2>  
  <form:form method="POST" action="/saveProject/1">  
      <table>  
       <tr>  
           <td><form:label path="id">ID:</form:label></td>  
           <td><form:input path="id" readonly="true"/></td>  
       </tr>  
       <tr>  
           <td><form:label path="name">Name:</form:label></td>  
           <td><form:input path="name" value="${project.name}"/></td>  
       </tr>  
         
       <tr>  
       <tr>  
        <td>&nbsp;</td>  
         <td><input type="submit" value="SAVE"/></td>  
         </tr>  
   </table>   
  </form:form>  
    
  
  <c:if test="${!empty projects}">  
 <table border="1">  
  <tr>  
   <th>ID</th>  
   <th>Name</th>  
   <th>Options</th>  
  </tr>  
  
  <c:forEach items="${projects}" var="project">  
   <tr>  
    <td><c:out value="${project.id}"/></td>  
    <td><c:out value="${project.name}"/></td>  
    <td align="center"><a href="editProject?id=${project.id}">Edit</a> | <a href="deleteProject?id=${project.id}">Delete</a></td>
   </tr>  
  </c:forEach>  
 </table>  
</c:if>  
</div>
</div>
</body>
<jsp:include page="../fragments/footer.jsp"></jsp:include>
</html>