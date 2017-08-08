<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>雇员详情</title>
</head>
<body>
<h1>ID为${employee.id}的雇员详情</h1>
    姓名：<c:out value="${employee.name}" /><br/>
    邮箱：<c:out value="${employee.email}" />
<br>
<a href="./">返回</a>
</body>
</html>