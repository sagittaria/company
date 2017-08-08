<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>雇员表单</title>
</head>
<body>
	<h1><c:out value="${FormFunc}" /></h1>

	<sf:form method="POST" commandName="employee">
		<sf:label path="name">name</sf:label>:
            <sf:input path="name" />
		<br />
		<sf:label path="email">email</sf:label>:
            <sf:input path="email"/>
		<br />
            <input type="submit" value="${FormFunc}" />
	</sf:form>

</body>
</html>