<%--
  Created by IntelliJ IDEA.
  User: Yisa
  Date: 2017/7/23
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="customer" items="${customerList}" >
    <tr>
        <td>${customer.name}</td>
    </tr>
</c:forEach>


</body>
</html>
