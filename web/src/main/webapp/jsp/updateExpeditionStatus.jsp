<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  File : updateExpeditionStatus.jsp
  Description : update expedition status

  Author : Popov Denys
  Created : 23/02/18

  Modified : { date: 23/02/18
              ,time: 20:19 PM }
  Modified by: Popov Denys

  Last modification : update status form
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
    <link rel="stylesheet" type="text/css" href="../interface/css/styles.css">
</head>
<body>
<h2>Update Expedition status</h2>
<table class="status">
    <tr><th>Expedition id</th><th>Status</th></tr>
    <c:forEach items="${expeditions}" var="expedition">
        <tr>
            <td>${expedition.id}</td>
            <td>
                <form action="processexpedition.html" method="post">
                    <input type="hidden" name="id" value="${expedition.id}">
                    <select name="status">
                        <option value="${expedition.status}" selected>${expedition.status}</option>
                        <c:forEach items="status" var="s">
                            <c:if test="${!s.equals(expedition.status)}">
                                <option value="${s}">${s}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jsp"/>

</body>
</html>
