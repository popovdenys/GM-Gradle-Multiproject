<%--
  File : searchResult.jsp
  Description : search result page

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 20/02/18
              ,time: 12:58 PM }
  Modified by: Popov Denys

  Last modification : jstl added
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Univers</title>
</head>
<body>
    <h3>Found route</h3>

    <ul>
        <c:forEach items="${foundGalaxies}" var="galaxy">
            <li>${galaxy}</li>
        </c:forEach>
    </ul>

    <p><span>${foundGalaxies.size()} Galaxies in total</span></p>

    <jsp:include page="footer.jsp"/>
</body>
</html>
