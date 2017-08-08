<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>所有雇员</title>
</head>
<body>
<a href="add">新增</a>
  <c:forEach items="${employeeList}" var="employee">
	<li>
	  <c:out value="${employee.id}" />, 
	  <c:out value="${employee.name}" />, 
	  <a href="${employee.id}">详情</a>, 
	  <a href="${employee.id}/edit">修改</a>,
	  <a href="${employee.id}/delete">删除</a>
	</li>
  </c:forEach>
</body>
</html>