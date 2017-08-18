<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>年龄分布</title>
</head>
<body>
<a href="./">返回</a>
      格式：“年龄，人数”
 <c:forEach items="${statisticList}" var="res">
    <li>
      <c:out value="${res.age}" />, 
      <c:out value="${res.count}" />,
    </li>
  </c:forEach>  

</body>
</html>