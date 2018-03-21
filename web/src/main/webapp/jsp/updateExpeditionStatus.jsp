<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  File : UpdateExpeditionStatus.jsp
  Description : update expedition status

  Author : Popov Denys
  Created : 23/02/18

  Modified : { date: 23/02/18
              ,time: 08:17App PM }
  Modified by: Popov Denys

  Last modification : update status form
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
    <link rel="stylesheet" type="text/css" href="interface/css/styles.css">
</head>
<body>
<h2>Update Expedition status</h2>
<table class="status">
    <tr><th>id</th><th>Destinations</th><th>Total distance<br/>(in Mly)</th><th>Contractor</th><th>Status</th></tr>
    <c:forEach items="${expeditions}" var="expedition">
        <tr>
            <td>${expedition.id}</td>
            <td>
                <c:forEach items="${expedition.itinerary}" var="galaxy">
                    <p>${galaxy.key}</p>
                </c:forEach>
            </td>
            <td>
                ${expedition.getExpeditionDistance(expedition.itinerary)}
            </td>
            <td>
                ${expedition.contractor}
            </td>
            <td>
                <form action="updateExpeditionStatus.html" method="post">

                    <input type="hidden" name="id" value="${expedition.id}">
                    <div>
                        <select name="status">
                            <option value="${expedition.status}" selected>${expedition.status}</option>
                            <c:forEach items="${status}" var="s">
                                <c:if test="${!s.equals(expedition.status)}">
                                    <option value="${s}">${s}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div><input type="submit" value="Update"></div>

                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jsp"/>

</body>
</html>
