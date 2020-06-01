<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Список машин</title>
</head>
<body>
<h3>Список машин</h3>
<a href="${pageContext.request.contextPath   }/cars?locale=en">Eng</a>
<a href="${pageContext.request.contextPath   }/cars?locale=ru">Rus</a>
<%--<h1><spring:message code="title" /></h1>--%>
<h1><c:out value = "${title}"/></h1>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Number</th>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.id}</td>
            <td>${car.name}</td>
            <td>${car.number}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
